package edu.java.domain.jdbc;

import edu.java.domain.dao.UsersDao;
import edu.java.dto.model.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class JdbcChatDao implements UsersDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public boolean add(User user) {
        return jdbcTemplate.update("INSERT INTO chat (id) VALUES (?)", user.id) > 0;
    }

    @Override
    public void remove(User user) {
        jdbcTemplate.update("DELETE FROM chat WHERE id = ?", user.id);
    }

    @Override
    public boolean update(User user, User newUser) {
        return jdbcTemplate.update("UPDATE chat SET id = ? WHERE id = ?", newUser.id, user.id) > 0;
    }

    @Override
    public List<@NotNull User> getAll() {
        return jdbcTemplate.query("SELECT * FROM chat", (rs, rowNum) -> new User(rs.getLong("id")));
    }

}
