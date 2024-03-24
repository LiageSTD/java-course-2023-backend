package edu.java.configuration.dbCConfiguration;

import edu.java.domain.jdbc.UsersDaoJdbc;
import edu.java.domain.jdbc.LinksDaoJdbc;
import edu.java.domain.jdbc.UsersLinksJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JdbcConfiguration {

    private final JdbcTemplate jdbcTemplate;

    @Bean("usersDaoJdbc")
    public UsersDaoJdbc chatDaoJdbc() {
        return new UsersDaoJdbc(jdbcTemplate);
    }
    @Bean
    public LinksDaoJdbc linksDaoJdbc() {
        return new LinksDaoJdbc(jdbcTemplate);
    }
    @Bean
    public UsersLinksJdbc usersLinksJdbc() {
        return new UsersLinksJdbc(jdbcTemplate);
    }
}
