package edu.java.domain.jdbc;

import edu.java.domain.dao.LinksDao;
import edu.java.dto.model.Link;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.ZoneId;
import java.util.List;
@RequiredArgsConstructor
public class LinksDaoJdbc implements LinksDao {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Link add(Link link) {
        return jdbcTemplate.queryForObject("INSERT INTO links (url, updated_at, unable_to_update) VALUES (?, ?, ?) RETURNING id",
                Link.class, link.getUrl(), link.getUpdatedAt(), link.isUnableToUpdate());
    }

    @Override
    public void remove(Link link) {
        jdbcTemplate.update("DELETE FROM links WHERE id = ?", link.getId());
    }

    @Override
    public List<@NotNull Link> getAll() {
        return jdbcTemplate.query("SELECT * FROM links", (rs, rowNum) -> new Link(rs.getLong("id"),
                rs.getString("url"), rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")));
    }

    @Override
    public Link findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM links WHERE id = ?", (rs, rowNum) -> new Link(rs.getLong("id"),
                rs.getString("url"), rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")), id);
    }

    @Override
    public Link findByUrl(String URL) {
        return jdbcTemplate.queryForObject("SELECT * FROM links WHERE url = ?", (rs, rowNum) -> new Link(rs.getLong("id"),
                rs.getString("url"), rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")), URL);
    }

    @Override
    public void update(Link link) {
        jdbcTemplate.update("UPDATE links SET url = ?, updated_at = ?, unable_to_update = ? WHERE id = ?", link.getUrl(),
                link.getUpdatedAt(), link.isUnableToUpdate(), link.getId());
    }

    @Override
    public List<Link> findUnupdatable() {
        return jdbcTemplate.query("SELECT * FROM links WHERE unable_to_update = true", (rs, rowNum) -> new Link(rs.getLong("id"),
                rs.getString("url"), rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")));
    }
}
