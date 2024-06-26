package edu.java.scrapper.configuration;

import edu.java.scrapper.configuration.db.AccessType;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull
    Scheduler scheduler,

    @NotNull
    AccessType databaseAccessType,

    @NotNull
    LinkUpdaterType linkUpdaterType

) {
    public record Scheduler(
        boolean enable,
        @NotNull Duration interval,
        @NotNull Duration forceCheckDelay,
        long scanLinksLimit) {
    }

    public enum LinkUpdaterType {
        KAFKA,
        HTTP
    }

}
