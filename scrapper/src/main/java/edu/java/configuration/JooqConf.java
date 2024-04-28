package edu.java.configuration;

import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConf {
    @Bean
    public DefaultConfigurationCustomizer configuration() {
        return (DefaultConfiguration c) -> c.set(
            new Settings().withRenderNameCase(
                RenderNameCase.LOWER).withRenderQuotedNames(RenderQuotedNames.NEVER)
        );
    }
}
