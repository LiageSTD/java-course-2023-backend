package edu.java.configuration.database;

import edu.java.domain.jooq.JooqChatDao;
import edu.java.domain.jooq.JooqChatLinkDao;
import edu.java.domain.jooq.JooqLinkDao;
import edu.java.service.databaseAccess.jooq.JooqChatService;
import edu.java.service.databaseAccess.jooq.JooqLinkService;
import org.jooq.DSLContext;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.database-access-type", havingValue = "JOOQ")
public class JooqConf {
    @Bean
    public DefaultConfigurationCustomizer configuration() {
        return (DefaultConfiguration c) -> c.set(
            new Settings().withRenderNameCase(
                RenderNameCase.LOWER).withRenderQuotedNames(RenderQuotedNames.NEVER)
        );
    }

    @Bean
    public JooqChatDao chatDao(DSLContext dslContext) {
        return new JooqChatDao(dslContext);
    }

    @Bean
    public JooqLinkDao linkDao(DSLContext dslContext) {
        return new JooqLinkDao(dslContext);
    }

    @Bean
    public JooqChatLinkDao chatLinkDao(DSLContext dslContext) {
        return new JooqChatLinkDao(dslContext);
    }

    @Bean JooqChatService chatService(JooqChatDao chatDao, JooqChatLinkDao chatLinkDao) {
        return new JooqChatService(chatDao, chatLinkDao);
    }

    @Bean
    public JooqLinkService linkService(JooqLinkDao linkDao, JooqChatLinkDao chatLinkDao) {
        return new JooqLinkService(linkDao, chatLinkDao);
    }
}
