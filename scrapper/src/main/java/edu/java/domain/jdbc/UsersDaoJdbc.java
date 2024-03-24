package edu.java.domain.jdbc;

import edu.java.domain.dao.UsersDao;
import edu.java.dto.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class UsersDaoJdbc implements UsersDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public boolean add(User user) {
        return jdbcTemplate.update("INSERT INTO users (id) VALUES (?)", user.id) > 0;
    }

    @Override
    public void remove(User user) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", user.id);
    }

    @Override
    public boolean update(User user, User newUser) {
        return jdbcTemplate.update("UPDATE users SET id = ? WHERE id = ?", newUser.id, user.id) > 0;
    }

    @Override
    public List<@NotNull User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> new User(rs.getLong("id")));
    }
}
