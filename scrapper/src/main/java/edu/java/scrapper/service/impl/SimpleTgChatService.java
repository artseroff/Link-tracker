package edu.java.scrapper.service.impl;

import edu.java.scrapper.domain.TgChatRepository;
import edu.java.scrapper.service.LinkService;
import edu.java.scrapper.service.TgChatService;
import edu.java.scrapper.service.exception.EntityAlreadyExistException;
import edu.java.scrapper.service.exception.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SimpleTgChatService implements TgChatService {
    private final TgChatRepository chatRepository;

    private final LinkService linkService;

    public SimpleTgChatService(
        TgChatRepository chatRepository,
        LinkService linkService
    ) {
        this.chatRepository = chatRepository;
        this.linkService = linkService;
    }

    @Override
    public void register(long chatId) throws EntityAlreadyExistException {
        if (chatRepository.findById(chatId).isPresent()) {
            throw new EntityAlreadyExistException("Чат уже зарегистрирован");
        }
        chatRepository.add(chatId);
    }

    @Override
    public void unregister(long chatId) throws EntityNotFoundException {
        chatRepository.findById(chatId)
            .orElseThrow(() -> new EntityNotFoundException("Чат не найден"));

        linkService.listAll(chatId)
            .forEach(linkDto -> {
                try {
                    linkService.untrack(chatId, linkDto.url());
                } catch (EntityNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

        chatRepository.remove(chatId);
    }

}
