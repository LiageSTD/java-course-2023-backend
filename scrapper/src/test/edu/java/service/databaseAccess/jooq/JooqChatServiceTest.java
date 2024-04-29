package edu.java.service.databaseAccess.jooq;

import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import static edu.java.domain.jooq.model.Tables.CHAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
@TestPropertySource(properties = "app.database-access-type=JOOQ")
class JooqChatServiceTest extends IntegrationTest {
    @Autowired
    private JooqChatService jooqChatService;
    @Autowired
    private JooqLinkService jooqLinkService;
    @Autowired
    private DSLContext dslContext;


    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        jooqChatService.add(user);
        assertEquals(1, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(user.getId())).fetch().size());
        assertEquals(1, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(user.getId())).fetchOne(CHAT.ID));
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        jooqChatService.add(user);
        jooqChatService.remove(user);
        assertEquals(0, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(user.getId())).fetch().size());
    }
    @Test
    @Transactional
    @Rollback
    void getAllTest() {
        User user = new User(1L);
        User anotherUser = new User(2L);
        jooqChatService.add(user);
        jooqChatService.add(anotherUser);
        assertEquals(2, jooqChatService.getAll().size());
    }
    @Test
    @Transactional
    @Rollback
    void getUsersByLinkTest() {
        User user = new User(1L);
        User anotherUser = new User(2L);
        Link link = new Link(1L, "https://www.google.com", OffsetDateTime.now(), false);
        jooqChatService.add(user);
        jooqChatService.add(anotherUser);
        jooqLinkService.add(user, link);
        jooqLinkService.add(anotherUser, link);
        Link addedLink = jooqLinkService.findByUrl(link.getUrl());
        assertEquals(2, jooqChatService.getUsersByLink(addedLink.getId()).length);
    }
}
