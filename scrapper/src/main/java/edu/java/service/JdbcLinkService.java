package edu.java.service;

import edu.java.domain.dao.LinksDao;
import edu.java.domain.dao.UsersLinksDao;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import edu.java.service.jdbc.LinksService;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RequiredArgsConstructor
public class JdbcLinkService implements LinksService {
    private final LinksDao linksDao;
    private final UsersLinksDao usersLinksDao;

    @Override
    public Link add(Link link) {
        Link linkInDb = linksDao.findByUrl(link.getUrl());
        if (linkInDb == null) {
            return linksDao.add(link);
        } else {
            return linkInDb;
        }
    }

    @Override
    public Link remove(Link link) {
        Link linkInDb = linksDao.findById(link.getId());
        if (linkInDb != null) {
            linksDao.remove(linkInDb);
            return linkInDb;
        } else {
            throw new IllegalArgumentException("Link not found");
        }
    }

    @Override
    public Link update(Link link) {
        Link linkInDb = linksDao.findById(link.getId());
        if (linkInDb != null) {
            linksDao.update(link);
            return link;
        } else {
            return linksDao.add(link);
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
}
