package edu.java.domain.jdbc;

import edu.java.domain.JdbcChatDao;
import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
class JdbcUsersTest extends IntegrationTest {
    @Autowired
    private JdbcChatDao jdbcChatDao;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        jdbcChatDao.add(user);
        List<User> users = jdbcChatDao.getAll();
        assertEquals(1, users.size());
        assertEquals(user.id, users.get(0).id);
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        jdbcChatDao.add(user);
        jdbcChatDao.remove(user);
        List<User> users = jdbcChatDao.getAll();
        assertEquals(0, users.size());
    }
}
