package edu.java.domain.jooq;

import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static edu.java.domain.jooq.model.Tables.CHAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
class JooqChatDaoTest extends IntegrationTest {
    @Autowired
    private JooqChatDao jooqChatDao;
    @Autowired
    private DSLContext dslContext;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        jooqChatDao.add(user);
        assertEquals(1, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(user.getId())).fetch().size());
        assertEquals(1, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(user.getId())).fetchOne(CHAT.ID));
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        jooqChatDao.add(user);
        jooqChatDao.remove(user);
        assertEquals(0, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(user.getId())).fetch().size());
    }

    @Test
    @Transactional
    @Rollback
    void updateTest() {
        User user = new User(1L);
        jooqChatDao.add(user);
        User newUser = new User(2L);
        jooqChatDao.update(user, newUser);
        assertEquals(0, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(user.getId())).fetch().size());
        assertEquals(1, dslContext.selectFrom(CHAT).where(CHAT.ID.eq(newUser.getId())).fetch().size());
    }
}
