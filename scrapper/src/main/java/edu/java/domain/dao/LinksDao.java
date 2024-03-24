package edu.java.domain.dao;

import edu.java.dto.model.Link;
import edu.java.dto.scrapper.response.LinkResponse;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface LinksDao {
    Link add(@NotNull Link link);
    void remove(@NotNull Link link);
    @NotNull List<@NotNull Link> getAll();
    @Nullable Link findById(Long id);
    @Nullable Link findByUrl(String URL);
    void update(@NotNull Link link);
    @Nullable List<Link> findUnupdatable();
}
