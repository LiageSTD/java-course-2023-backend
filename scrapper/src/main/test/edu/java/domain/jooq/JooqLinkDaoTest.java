package edu.java.domain.jooq;

import edu.java.dto.model.Link;
import edu.java.scrapper.IntegrationTest;
import java.time.OffsetDateTime;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static edu.java.domain.jooq.model.Tables.LINK;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
class JooqLinkDaoTest extends IntegrationTest {
    @Autowired
    private JooqLinkDao jooqLinkDao;
    @Autowired
    private DSLContext dslContext;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        Link link = new Link(1L, "url", OffsetDateTime.now(), false);
        jooqLinkDao.add(link);
        assertEquals(1, dslContext.selectFrom(LINK).where(LINK.URL.eq("url")).fetch().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        Link link = new Link(1L, "url", OffsetDateTime.now(), false);
        jooqLinkDao.add(link);
        jooqLinkDao.remove(link);
        assertEquals(0, dslContext.selectFrom(LINK).where(LINK.URL.eq("url")).fetch().size());
    }

    @Test
    @Transactional
    @Rollback
    void updateTest() {
        Link link = new Link(1L, "url", OffsetDateTime.now(), false);
        jooqLinkDao.add(link);
        Link newLink = new Link(2L, "url", OffsetDateTime.now(), true);
        jooqLinkDao.update(newLink);
        assertEquals(true, dslContext.selectFrom(LINK).where(LINK.URL.eq("url")).fetchOne().getUnableToUpdate());
    }

}
