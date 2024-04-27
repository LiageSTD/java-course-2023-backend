package edu.java.service;

import edu.java.domain.dao.LinksDao;
import edu.java.domain.dao.UsersLinksDao;
import edu.java.domain.jdbc.LinkService;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JdbcLinkService implements LinkService {
    private final LinksDao linksDao;
    private final UsersLinksDao usersLinksDao;

    @Override
    public Link add(User user, Link link) {
        Link linkInDb = linksDao.findByUrl(link.getUrl());
        if (linkInDb == null) {
            Link newLink = new Link(linksDao.add(link), link.getUrl(), link.getUpdatedAt(), link.isUnableToUpdate());
            usersLinksDao.add(user, newLink);
            return newLink;
        } else {
            usersLinksDao.add(user, linkInDb);
            return linkInDb;
        }
    }

    @Override
    public Link remove(User user, Link link) {
        Link linkInDb = linksDao.findById(link.getId());
        if (linkInDb != null) {
            linksDao.remove(linkInDb);
            usersLinksDao.remove(user, linkInDb);
            return linkInDb;
        } else {
            throw new IllegalArgumentException("Link not found");
        }
    }

    @Override
    public Link update(User user, Link link) {
        Link linkInDb = linksDao.findById(link.getId());
        if (linkInDb != null) {
            linksDao.update(link);
            usersLinksDao.remove(user, linkInDb);
            return link;
        } else {
            return new Link(linksDao.add(link), link.getUrl(), link.getUpdatedAt(), link.isUnableToUpdate());
        }
    }

    @Override
    public List<Link> findAllByUserId(User user) {
        return usersLinksDao.findAllByUserId(user);
    }

    @Override
    public Link findById(User user) {
        return linksDao.findById(user.getId());
    }

    @Override
    public Collection<Link> listAllByTime(OffsetDateTime updatedAt) {
        return linksDao.listAllByTime(updatedAt);
    }
}
