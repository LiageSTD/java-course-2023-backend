package edu.java.domain.jdbc;

import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public interface LinkService {
    Link add(User user, Link link);

    Link remove(User user, Link link);

    Link update(User user, Link link);

    List<Link> findAllByUserId(User user);

    Link findById(User user);

    Collection<Link> listAllByTime(OffsetDateTime updatedAt);
}
