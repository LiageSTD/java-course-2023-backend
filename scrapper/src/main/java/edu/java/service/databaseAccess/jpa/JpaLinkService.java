package edu.java.service.databaseAccess.jpa;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.domain.jpa.JpaLinkDao;
import edu.java.domain.jpa.model.Chat;
import edu.java.domain.jpa.model.Link;
import edu.java.dto.model.User;
import edu.java.service.databaseAccess.LinkService;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class JpaLinkService implements LinkService {
    private final JpaLinkDao linkDao;
    private final JpaChatDao chatDao;

    @Override
    public edu.java.dto.model.Link add(User user, edu.java.dto.model.Link link) {
        Chat chat = chatDao.findById(user.id).orElse(null);
        if (chat == null) {
            return null;
        }

        Link linkInDb = linkDao.findByUrl(link.getUrl()).orElseGet(() -> {
            Link defaultLink = new Link();
            defaultLink.setUrl(link.getUrl());
            defaultLink.setUnableToUpdate(false);
            defaultLink.setUpdatedAt(OffsetDateTime.now());
            return linkDao.save(defaultLink);
        });

        Set<Link> links = chat.getLinks();
        if (links == null) {
            chat.setLinks(new HashSet<>());
        }
        chat.getLinks().add(linkInDb);
        chatDao.save(chat);
        return new edu.java.dto.model.Link(
            linkInDb.getId(),
            linkInDb.getUrl(),
            linkInDb.getUpdatedAt(),
            linkInDb.getUnableToUpdate()
        );
    }

    @Override
    public edu.java.dto.model.Link remove(User user, edu.java.dto.model.Link link) {
        Chat chat = chatDao.findById(user.id).orElse(null);
        Link linkInDb = linkDao.findByUrl(link.getUrl()).orElse(null);
        if (chat == null || linkInDb == null) {
            return null;
        }
        chat.getLinks().remove(linkInDb);
        chatDao.save(chat);
        return new edu.java.dto.model.Link(
            linkInDb.getId(),
            linkInDb.getUrl(),
            linkInDb.getUpdatedAt(),
            linkInDb.getUnableToUpdate()
        );
    }

    @Override
    public edu.java.dto.model.Link update(User user, edu.java.dto.model.Link link) {
        Chat chat = chatDao.findById(user.id).orElse(null);
        Link linkInDb = linkDao.findByUrl(link.getUrl()).orElse(null);
        if (chat == null || linkInDb == null) {
            return null;
        }
        linkInDb.setUnableToUpdate(link.isUnableToUpdate());
        linkInDb.setUpdatedAt(OffsetDateTime.now());
        linkDao.save(linkInDb);
        return new edu.java.dto.model.Link(
            linkInDb.getId(),
            linkInDb.getUrl(),
            linkInDb.getUpdatedAt(),
            linkInDb.getUnableToUpdate()
        );
    }

    @Override
    public List<edu.java.dto.model.Link> findAllByUserId(User user) {
        return chatDao.findById(user.id)
            .map(Chat::getLinks)
            .map(links -> links.stream()
                .map(link -> new edu.java.dto.model.Link(
                    link.getId(),
                    link.getUrl(),
                    link.getUpdatedAt(),
                    link.getUnableToUpdate()
                ))
                .toList()
            ).orElse(List.of());
    }

    @Nullable
    @Override
    public edu.java.dto.model.Link findByUrl(String url) {
        return linkDao.findByUrl(url)
            .map(link -> new edu.java.dto.model.Link(
                link.getId(),
                link.getUrl(),
                link.getUpdatedAt(),
                link.getUnableToUpdate()
            ))
            .orElse(null);
    }

    @Override
    public edu.java.dto.model.Link findById(User user) {
        return linkDao.findById(user.getId())
            .map(link -> new edu.java.dto.model.Link(
                link.getId(),
                link.getUrl(),
                link.getUpdatedAt(),
                link.getUnableToUpdate()
            ))
            .orElse(null);
    }

    @Override
    public Collection<edu.java.dto.model.Link> listAllByTime(OffsetDateTime updatedAt) {
        return linkDao.findAllByUpdatedAtBefore(updatedAt).stream()
            .map(link -> new edu.java.dto.model.Link(
                link.getId(),
                link.getUrl(),
                link.getUpdatedAt(),
                link.getUnableToUpdate()
            ))
            .toList();
    }
}
