package edu.java.domain.jdbc;

import edu.java.domain.daoModel.ChatDao;
import edu.java.dto.model.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcChatDao implements ChatDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean add(Long userId) {
        return jdbcTemplate.update("INSERT INTO chat (id) VALUES (?)", userId) > 0;
    }

    @Override
    public void remove(Long userId) {
        jdbcTemplate.update("DELETE FROM chat WHERE id = ?", userId);
    }

    @Override
    public boolean update(User user, User newUser) {
        return jdbcTemplate.update("UPDATE chat SET id = ? WHERE id = ?", newUser.id, user.id) > 0;
    }

    @Override
    public List<@NotNull User> getAll() {
        return jdbcTemplate.query("SELECT * FROM chat", (rs, rowNum) -> new User(rs.getLong("id")));
    }

    @Override
    public boolean exists(User user) {
        return !jdbcTemplate.query(
            "SELECT * FROM chat WHERE id = ?", (rs, rowNum) -> new User(rs.getLong("id")), user.id
        ).isEmpty();
    }

}
