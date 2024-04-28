package edu.java.domain.jdbc;

import edu.java.domain.dao.UsersLinksDao;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository @RequiredArgsConstructor public class JdbcChatLinkDao implements UsersLinksDao {
    private final JdbcTemplate jdbcTemplate;

    @Override public Boolean add(User user, Link link) {
        return
            jdbcTemplate
                .update(
                    "INSERT INTO chat_link (chat_id, link_id) VALUES (?, ?)", user.getId(), link.getId()
                ) > 0;
    }

    @Override public void remove(User chat, Link link) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE chat_id = ? AND link_id = ?", chat.getId(), link.getId());
    }

    @Override public void removeByChat(User chat) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE chat_id = ?", chat.getId());
    }

    @Override public void removeByLink(Link link) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE link_id = ?", link.getId());
    }

    @Override public List<Link> findAllByUserId(User user) {
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE id IN (SELECT link_id FROM chat_link WHERE chat_id = ?)",
            (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ),
            user.getId()
        );
    }

    @Override public long[] getUserIdsByLink(long linkId) {
        return jdbcTemplate.queryForList("SELECT chat_id FROM chat_link WHERE link_id = ?", Long.class, linkId).stream()
            .mapToLong(Long::longValue).toArray();
    }
}
