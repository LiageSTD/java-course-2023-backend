package edu.java.service.databaseAccess.jdbc;

import edu.java.domain.daoModel.LinksDao;
import edu.java.domain.daoModel.UsersLinksDao;
import edu.java.domain.jdbc.JdbcChatLinkDao;
import edu.java.domain.jdbc.JdbcLinkDao;
import edu.java.service.databaseAccess.LinkService;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class JdbcLinkService implements LinkService {

    private final JdbcLinkDao linksDao;
    private final JdbcChatLinkDao usersLinksDao;

    @Override
    public Link add(User user, Link link) {
        if (linksDao.exists(link.getUrl())) {
            Link linkInDb = linksDao.findByUrl(link.getUrl());
            usersLinksDao.add(user.id, linkInDb.getId());
            return linkInDb;
        } else {
            Long linkId = linksDao.add(link.getUrl());
            link.setId(linkId);
            usersLinksDao.add(user.id, link.getId());
            return link;
        }
    }

    @Override
    public Link remove(User user, Link link) {
        if (linksDao.exists(link.getUrl())) {
            Link linkInDb = linksDao.findByUrl(link.getUrl());
            usersLinksDao.remove(user.id, linkInDb.getId());
            if (usersLinksDao.getUserIdsByLink(link.getId()).length == 0) {
                linksDao.remove(link.getUrl());
            }
        }
        return link;
        //TODO: strange remove behavior (Doesn't remove link from db if it's not in user's links)
    }

    @Override
    public Link update(User user, Link link) {
        if (linksDao.exists(link.getUrl())) {
            linksDao.update(link);
        }
        return link;
    }

    @Override
    public List<Link> findAllByUserId(User user) {
        return usersLinksDao.findAllByUserId(user.id);
    }

    @Override
    public Link findByUrl(String url) {
        return linksDao.findByUrl(url);
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
