package edu.java.configuration;

import edu.java.configuration.database.AccessType;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@Validated @ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)

public record ApplicationConfig(

    @NotNull Scheduler scheduler,
    @NotNull Integer webClientMaxInMemorySize,
    @NotNull AccessType databaseAccessType) {
    @Bean public Scheduler scheduler() {
        return scheduler;
    }

    public Duration getUpdateInterval() {
        return scheduler.interval;
    }

    public record Scheduler(boolean enable, @NotNull Duration interval, @NotNull Duration forceCheckDelay) {
    }

}
