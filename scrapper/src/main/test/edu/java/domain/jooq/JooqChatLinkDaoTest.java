package edu.java.domain.jooq;

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
import static edu.java.domain.jooq.model.Tables.CHAT_LINK;
import static edu.java.domain.jooq.model.Tables.LINK;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
class JooqChatLinkDaoTest extends IntegrationTest {
    @Autowired
    private JooqChatDao jooqChatDao;
    @Autowired
    private JooqChatLinkDao jooqChatLinkDao;
    @Autowired
    private DSLContext dslContext;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        Link link = new Link(1L, "url", OffsetDateTime.now(), false);
        jooqChatDao.add(user);
        jooqChatLinkDao.add(user, link);
        assertEquals(1, dslContext.selectFrom(LINK).where(LINK.URL.eq(link.getUrl())).fetch().size());
        assertEquals(1, dslContext.selectFrom(CHAT_LINK).where(CHAT_LINK.CHAT_ID.eq(user.getId())).fetch().size());

    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        Link link = new Link(1L, "url", OffsetDateTime.now(), false);
        jooqChatDao.add(user);
        jooqChatLinkDao.add(user, link);
        jooqChatLinkDao.remove(user, link);
        assertEquals(0, dslContext.selectFrom(CHAT_LINK).where(CHAT_LINK.LINK_ID.eq(link.getId())).fetch().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeByChatTest() {
        User user = new User(1L);
        Link link = new Link(1L, "url", OffsetDateTime.now(), false);
        jooqChatDao.add(user);
        jooqChatLinkDao.add(user, link);
        jooqChatLinkDao.removeByChat(user);
        assertEquals(0, dslContext.selectFrom(CHAT_LINK).where(CHAT_LINK.CHAT_ID.eq(user.getId())).fetch().size());
    }
}
