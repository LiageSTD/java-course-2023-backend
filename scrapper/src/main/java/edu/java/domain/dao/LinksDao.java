package edu.java.domain.dao;

import edu.java.dto.model.Link;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public interface LinksDao {
    Long add(@NotNull Link link);

    void remove(@NotNull Link link);

    @NotNull List<@NotNull Link> getAll();

    @Nullable Link findById(Long id);

    @Nullable Link findByUrl(String url);

    void update(@NotNull Link link);

    @Nullable List<Link> findUnupdatable();

    @Nullable Collection<Link> listAllByTime(@NotNull OffsetDateTime updatedAt);

    boolean exists(@NotNull Link link);
}
