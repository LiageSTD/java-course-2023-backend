package edu.java.service.databaseAccess.jpa;

import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
@TestPropertySource(properties = "app.database-access-type=JPA")
class JpaLinkServiceTest extends IntegrationTest {
    @Autowired
    private JpaLinkService jpaLinkService;
    @Autowired
    private JpaChatService jpaChatService;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jpaChatService.add(user);
        jpaLinkService.add(user, link);
        assertEquals("google.com", jpaLinkService.findByUrl(link.getUrl()).getUrl());
    }
    @Test
    @Transactional
    @Rollback
    void removeTest() {
        User user = new User(1L);
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jpaChatService.add(user);
        jpaLinkService.add(user, link);
        jpaLinkService.remove(user, link);
        assertEquals(0, jpaLinkService.findAllByUserId(user).size());
    }
    @Test
    @Transactional
    @Rollback
    void findAllByUserIdTest() {
        User user = new User(1L);
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jpaChatService.add(user);
        jpaLinkService.add(user, link);
        assertEquals(1, jpaLinkService.findAllByUserId(user).size());
    }
    @Test
    @Transactional
    @Rollback
    void findByUrlTest() {
        User user = new User(1L);
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jpaChatService.add(user);
        jpaLinkService.add(user, link);
        assertNotNull(jpaLinkService.findByUrl(link.getUrl()));
    }
    @Test
    @Transactional
    @Rollback
    void updateTest() {
        User user = new User(1L);
        Link link = new Link(1L, "google.com", OffsetDateTime.now(), false);
        jpaChatService.add(user);
        jpaLinkService.add(user, link);
        link.setUnableToUpdate(true);
        jpaLinkService.update(user, link);
        assertTrue(jpaLinkService.findByUrl(link.getUrl()).isUnableToUpdate());
    }


}
