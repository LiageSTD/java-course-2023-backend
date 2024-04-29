package edu.java.domain.jpa;

import edu.java.domain.jpa.model.Link;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLinkDao extends JpaRepository<Link, Long> {
    Collection<Link> findAllByUpdatedAtBefore(OffsetDateTime updatedAt);

    Optional<Link> findByUrl(String url);
}
