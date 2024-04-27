package edu.java.domain.jdbc;

import edu.java.dto.model.Link;
import java.time.OffsetDateTime;
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
class JdbcLinkTest {
    @Autowired
    private JdbcLinkDao jdbcChatDao;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        Long id = jdbcChatDao.add(link);
        List<Link> links = jdbcChatDao.getAll();
        assertEquals(1, links.size());
        assertEquals(link.getUrl(), links.get(0).getUrl());
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jdbcChatDao.add(link);
        link = jdbcChatDao.findByUrl("google.com");
        jdbcChatDao.remove(link);
        List<Link> links = jdbcChatDao.getAll();
        assertEquals(0, links.size());
    }
}
