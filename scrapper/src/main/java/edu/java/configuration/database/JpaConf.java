package edu.java.configuration.database;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.domain.jpa.JpaLinkDao;
import edu.java.service.databaseAccess.ChatService;
import edu.java.service.databaseAccess.LinkService;
import edu.java.service.databaseAccess.jpa.JpaChatService;
import edu.java.service.databaseAccess.jpa.JpaLinkService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "JPA")
public class JpaConf {

    @Bean
    public JpaLinkService jpaLinkService(JpaLinkDao jpaLinkDao, JpaChatDao jpaChatDao) {
        return new JpaLinkService(jpaLinkDao, jpaChatDao);
    }
    @Bean
    public JpaChatService jpaChatService(JpaChatDao jpaChatDao) {
        return new JpaChatService(jpaChatDao);
    }
}
