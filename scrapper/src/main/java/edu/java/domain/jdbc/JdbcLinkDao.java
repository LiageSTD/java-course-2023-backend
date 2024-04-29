package edu.java.domain.jdbc;

import edu.java.domain.daoModel.LinksDao;
import edu.java.dto.model.Link;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@SuppressWarnings("MultipleStringLiterals")
public class JdbcLinkDao implements LinksDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long add(String url) {
        return jdbcTemplate.queryForObject(
            "INSERT INTO link (url, updated_at, unable_to_update) VALUES (?, ?, ?) RETURNING id",
            Long.class,
            url,
            OffsetDateTime.now(),
            false
        );
    }

    @Override
    public void remove(String url) {
        jdbcTemplate.update("DELETE FROM link WHERE url = ?", url);
    }

    @Override
    public List<@NotNull Link> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM link", (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ));
        } catch (final EmptyResultDataAccessException ignored) {
            return List.of();
        }
    }

    @Override
    public Link findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM link WHERE id = ?", (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ), id);
        } catch (final EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public Link findByUrl(String url) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM link WHERE url = ?", (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ), url);
        } catch (final EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public void update(Link link) {
        jdbcTemplate.update("UPDATE link SET url = ?, updated_at = ?, unable_to_update = ? WHERE id = ?", link.getUrl(),
            link.getUpdatedAt(), link.isUnableToUpdate(), link.getId()
        );
    }

    @Nullable
    @Override
    public Collection<Link> listAllByTime(OffsetDateTime updatedAt) {
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE updated_at < ?",
            (rs, rowNum) -> new Link(
                rs.getLong("id"),
                rs.getString("url"),
                rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                rs.getBoolean("unable_to_update")
            ),
            updatedAt
        );
    }

    @Override
    public boolean exists(String url) {
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM link WHERE url = ?", Long.class, url);
        return count != null && count > 0;
    }
}
