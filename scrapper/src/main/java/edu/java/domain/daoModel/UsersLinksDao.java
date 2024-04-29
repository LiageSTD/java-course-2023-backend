package edu.java.domain.daoModel;

import edu.java.dto.model.Link;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface UsersLinksDao {
    Boolean add(@NotNull Long userId, @NotNull Long linkId);

    void remove(@NotNull Long userId, @NotNull Long linkId);

    void removeByChatId(@NotNull Long userId);

    void removeByLink(@NotNull Long linkId);

    List<Link> findAllByUserId(@NotNull Long userId);

    long[] getUserIdsByLink(long linkId);
}
