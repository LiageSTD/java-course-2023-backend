package edu.java.domain.jpa;

import edu.java.domain.jpa.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Optional;


public interface JpaLinkDao extends JpaRepository<Link, Long> {
    Collection<Link> findAllByUpdatedAtBefore(OffsetDateTime updatedAt);
    Optional<Link> findByUrl(String url);
}
