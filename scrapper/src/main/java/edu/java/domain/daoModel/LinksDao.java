package edu.java.domain.daoModel;

import edu.java.dto.model.Link;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public interface LinksDao {
    Long add(@NotNull String url);

    void remove(@NotNull String url);

    @NotNull List<@NotNull Link> getAll();

    @Nullable Link findById(Long id);

    @Nullable Link findByUrl(String url);

    void update(@NotNull Link link);

    @Nullable Collection<Link> listAllByTime(@NotNull OffsetDateTime updatedAt);

    boolean exists(@NotNull String url);
}
