package edu.java.service.databaseAccess.jpa;

import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
@TestPropertySource(properties = "app.database-access-type=JPA")
class JpaChatServiceTest extends IntegrationTest {
    @Autowired
    JpaChatService jpaChatService;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        jpaChatService.add(user);
        List<User> users = jpaChatService.getAll();
        assertEquals(1, users.size());
        assertEquals(user.id, users.get(0).id);
    }
    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        jpaChatService.add(user);
        jpaChatService.remove(user);
        List<User> users = jpaChatService.getAll();
        assertEquals(0, users.size());
    }
    @Test
    @Transactional
    @Rollback
    void getAllTest() {
        User user = new User(1L);
        User anotherUser = new User(2L);
        jpaChatService.add(user);
        jpaChatService.add(anotherUser);
        assertEquals(2, jpaChatService.getAll().size());
    }
    @Test
    @Transactional
    @Rollback
    void findByIdTest() {
        User user = new User(1L);
        jpaChatService.add(user);
        assertTrue(jpaChatService.exists(user.id));
    }
    @Test
    @Transactional
    @Rollback
    void getUsersByLinkTest() {
        User user = new User(1L);
        User anotherUser = new User(2L);
        jpaChatService.add(user);
        jpaChatService.add(anotherUser);
        assertEquals(2, jpaChatService.getAll().size());
    }
}
