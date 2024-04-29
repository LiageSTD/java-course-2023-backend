package edu.java.domain.jdbc;

import edu.java.domain.daoModel.UsersLinksDao;
import edu.java.dto.model.Link;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository @RequiredArgsConstructor public class JdbcChatLinkDao implements UsersLinksDao {
    private final JdbcTemplate jdbcTemplate;

    @Override public Boolean add(Long userId, Long linkId) {
        return
            jdbcTemplate
                .update(
                    "INSERT INTO chat_link (chat_id, link_id) VALUES (?, ?)", userId, linkId
                ) > 0;
    }

    @Override public void remove(Long chatId, Long linkId) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE chat_id = ? AND link_id = ?", chatId, linkId);
    }

    @Override public void removeByChatId(Long chatId) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE chat_id = ?", chatId);
    }

    @Override public void removeByLink(Long linkId) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE link_id = ?", linkId);
    }

    @Override public List<Link> findAllByUserId(Long userId) {
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE id IN (SELECT link_id FROM chat_link WHERE chat_id = ?)",
            (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ),
            userId
        );
    }

    @Override public long[] getUserIdsByLink(long linkId) {
        return jdbcTemplate.queryForList("SELECT chat_id FROM chat_link WHERE link_id = ?", Long.class, linkId).stream()
            .mapToLong(Long::longValue).toArray();
    }
}
