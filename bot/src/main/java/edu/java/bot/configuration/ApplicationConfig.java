package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotEmpty
    String telegramToken,
    @NotEmpty
    String telegramBotName

) {
}
