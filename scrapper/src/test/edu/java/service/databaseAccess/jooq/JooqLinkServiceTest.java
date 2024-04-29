package edu.java.service.databaseAccess.jooq;

import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import java.time.OffsetDateTime;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static edu.java.domain.jooq.model.tables.ChatLink.CHAT_LINK;
import static edu.java.domain.jooq.model.tables.Link.LINK;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
@TestPropertySource(properties = "app.database-access-type=JOOQ")
class JooqLinkServiceTest extends IntegrationTest {
    @Autowired
    private JooqLinkService jooqLinkService;
    @Autowired
    private JooqChatService jooqChatService;
    @Autowired
    private DSLContext dslContext;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        Link link = new Link(1L, "https://www.google.com", OffsetDateTime.now(), false);
        jooqChatService.add(user);
        jooqLinkService.add(user, link);
        assertEquals(1, dslContext.selectFrom(LINK).where(LINK.URL.eq(link.getUrl())).fetch().size());
        assertEquals(1, dslContext.selectFrom(CHAT_LINK).where(CHAT_LINK.CHAT_ID.eq(user.getId())).fetch().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        Link link = new Link(1L, "https://www.google.com", OffsetDateTime.now(), false);
        jooqChatService.add(user);
        Link addedLink = jooqLinkService.add(user, link);
        jooqLinkService.remove(user, addedLink);
        assertEquals(0, dslContext.selectFrom(CHAT_LINK).where(CHAT_LINK.CHAT_ID.eq(user.getId())).fetch().size());
    }

    @Test
    @Transactional
    @Rollback
    void updateTest() {
        User user = new User(1L);
        Link link = new Link(1L, "https://www.google.com", OffsetDateTime.now(), false);
        Link newLink = new Link(1L, "https://www.google.com", OffsetDateTime.now(), true);
        jooqChatService.add(user);
        jooqLinkService.add(user, link);
        jooqLinkService.update(user, newLink);
        Link updatedLink = jooqLinkService.findByUrl(link.getUrl());
        assertEquals(newLink.isUnableToUpdate(), updatedLink.isUnableToUpdate());
    }
}
