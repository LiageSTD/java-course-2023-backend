package edu.java.service.databaseAccess.jdbc;

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
@TestPropertySource(properties = "app.database-access-type=JDBC")
class JdbcChatServiceTest extends IntegrationTest {
    @Autowired
    private JdbcChatService jdbcChatService;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        jdbcChatService.add(user);
        List<User> users = jdbcChatService.getAll();
        assertEquals(1, users.size());
        assertEquals(user.id, users.get(0).id);
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        jdbcChatService.add(user);
        jdbcChatService.remove(user);
        List<User> users = jdbcChatService.getAll();
        assertEquals(0, users.size());
    }
}
