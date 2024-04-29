package edu.java.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import edu.java.configuration.database.AccessType;

import java.time.Duration;

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
