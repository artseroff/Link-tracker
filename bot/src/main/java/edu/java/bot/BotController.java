package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.SendResponse;
import edu.java.bot.command.ActionCommand;
import edu.java.bot.command.factory.ActionFactory;
import edu.java.bot.command.raw.ParameterizableTextCommand;
import edu.java.bot.service.StatusCodeUtils;
import edu.java.general.ApiException;
import io.micrometer.core.instrument.Counter;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@Component
@Slf4j
public class BotController implements AutoCloseable {
    private static final String BOT_PROCESS_ONLY_PLAIN_TEXT = "Введите новое сообщение. "
        + "Бот не умеет считывать изменения прошлых собщений и может "
        + "принимать только сообщения из обычного текста без стикеров и ссылок на другие чаты.";

    private final Set<ActionCommand> commands;
    private final ActionFactory actionFactory;
    private final TelegramBot telegramBot;

    private final Counter proceedMessagesCounter;

    private final Counter errorsCounter;

    @Autowired
    public BotController(Set<ActionCommand> commands, TelegramBot telegramBot,
        Counter proceedMessagesCounter,
        Counter errorsCounter
    ) {
        this.commands = commands;
        this.actionFactory = new ActionFactory(commands);
        this.telegramBot = telegramBot;
        this.proceedMessagesCounter = proceedMessagesCounter;
        this.errorsCounter = errorsCounter;
    }

    @Override
    public void close() {
        telegramBot.removeGetUpdatesListener();
        telegramBot.shutdown();
    }

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        setUpdatesListener();
        setBotMenu();
    }

    private void setBotMenu() {
        BotCommand[] botCommands = commands.stream()
            .map(command -> new BotCommand(command.command(), command.description()))
            .toArray(BotCommand[]::new);
        telegramBot.execute(new SetMyCommands(botCommands));
    }

    private void setUpdatesListener() {
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {

                SendMessage message = handleUpdate(update);

                sendMessage(message);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> log.error(e.getMessage()));
    }

    public SendMessage handleUpdate(Update update) {
        if (update.message() == null) {
            long chatId = getChatId(update);
            return new SendMessage(
                chatId,
                BOT_PROCESS_ONLY_PLAIN_TEXT
            );
        }
        SendMessage sendMessage;
        try {

            ParameterizableTextCommand textCommand =
                ParameterizableTextCommand.buildTextCommandFromUpdate(update);
            ActionCommand command = actionFactory.defineCommand(textCommand);
            sendMessage = command.execute(textCommand);
            proceedMessagesCounter.increment();
        } catch (IllegalArgumentException | ApiException | WebClientRequestException e) {
            long chatId = update.message().chat().id();
            String errorText = prepareExceptionMessage(e);
            sendMessage = new SendMessage(chatId, errorText);
        }
        return sendMessage;
    }

    private long getChatId(Update update) {
        long chatId = 0;
        if (update.editedMessage() != null) {
            chatId = update.editedMessage().chat().id();
        }
        if (update.inlineQuery() != null) {
            chatId = update.inlineQuery().from().id();
        }
        if (update.chosenInlineResult() != null) {
            chatId = update.chosenInlineResult().from().id();
        }
        if (update.callbackQuery() != null) {
            chatId = update.callbackQuery().from().id();
        }
        return chatId;
    }

    private String prepareExceptionMessage(RuntimeException e) {
        String errorText = e.getMessage();
        if (e instanceof WebClientRequestException
            || (e instanceof ApiException && StatusCodeUtils.is5xxServerError(((ApiException) e).getCode()))) {
            errorText = "Сервис отслеживания ссылок недоступен";
            errorsCounter.increment();
        } else {
            proceedMessagesCounter.increment();
        }
        return errorText;
    }

    public void sendMessage(SendMessage message) {
        SendResponse response = telegramBot.execute(message);
        if (!response.isOk()) {
            log.error("%s;%s".formatted(response.errorCode(), response.description()));
        }
    }

}
