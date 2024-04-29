package edu.java.service.databaseAccess.jdbc;

import edu.java.domain.jdbc.JdbcLinkDao;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
@TestPropertySource(properties = "app.database-access-type=JDBC")
class JdbcLinkServiceTest extends IntegrationTest {
    @Autowired
    private JdbcLinkService jdbcLinkService;
    @Autowired
    private JdbcChatService jdbcChatService;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jdbcChatService.add(user);
        jdbcLinkService.add(user, link);
        assertNotNull(jdbcLinkService.findByUrl(link.getUrl()));
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jdbcChatService.add(user);
        jdbcLinkService.add(user, link);
        jdbcLinkService.remove(user, link);
        assertEquals(0,jdbcLinkService.findAllByUserId(user).size());
        assertNull(jdbcLinkService.findByUrl(link.getUrl()));
    }
}
