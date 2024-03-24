package jdbc;

import edu.java.domain.jdbc.UsersDaoJdbc;
import edu.java.dto.model.User;
import edu.java.scrapper.IntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
@TestPropertySource(properties = "spring.liquibase.enabled=false")
class JdbcUsersTest extends IntegrationTest {
    private final UsersDaoJdbc usersDaoJdbc;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        User user = new User(1L);
        usersDaoJdbc.add(user);
        List<User> users = usersDaoJdbc.getAll();
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
    }
}
