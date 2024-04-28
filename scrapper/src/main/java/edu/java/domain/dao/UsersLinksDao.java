package edu.java.domain.dao;

import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface UsersLinksDao {
    Boolean add(@NotNull User user, @NotNull Link link);

    void remove(@NotNull User user, @NotNull Link link);

    void removeByChat(@NotNull User user);

    void removeByLink(@NotNull Link link);

    List<Link> findAllByUserId(@NotNull User user);

    long[] getUserIdsByLink(long linkId);
}
