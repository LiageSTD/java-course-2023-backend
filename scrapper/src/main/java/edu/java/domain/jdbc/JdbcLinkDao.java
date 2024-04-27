package edu.java.domain.jdbc;

import edu.java.domain.dao.LinksDao;
import edu.java.dto.model.Link;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@SuppressWarnings("MultipleStringLiterals")
public class JdbcLinkDao implements LinksDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long add(Link link) {
        return jdbcTemplate.queryForObject(
            "INSERT INTO link (url, updated_at, unable_to_update) VALUES (?, ?, ?) RETURNING id ",
            Long.class,
            link.getUrl(),
            link.getUpdatedAt(),
            link.isUnableToUpdate()
        );
    }

    @Override
    public void remove(Link link) {
        jdbcTemplate.update("DELETE FROM link WHERE id = ?", link.getId());
    }

    @Override
    public List<@NotNull Link> getAll() {
        return jdbcTemplate.query("SELECT * FROM link", (rs, rowNum) -> new Link(
            rs.getLong("id"),
            rs.getString("url"),
            rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            rs.getBoolean("unable_to_update")
        ));
    }

    @Override
    public Link findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM link WHERE id = ?", (rs, rowNum) -> new Link(
            rs.getLong("id"),
            rs.getString("url"),
            rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            rs.getBoolean("unable_to_update")
        ), id);
    }

    @Override
    public Link findByUrl(String url) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM link WHERE url = ?",
            (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ),
            url
        );
    }

    @Override
    public void update(Link link) {
        jdbcTemplate.update("UPDATE link SET url = ?, updated_at = ?, unable_to_update = ? WHERE id = ?", link.getUrl(),
            link.getUpdatedAt(), link.isUnableToUpdate(), link.getId()
        );
    }

    @Override
    public List<Link> findUnupdatable() {
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE unable_to_update = true",
            (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            )
        );
    }

    @Nullable
    @Override
    public Collection<Link> listAllByTime(OffsetDateTime updatedAt) {
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE updated_at = ?",
            (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ),
            updatedAt
        );
    }
}
