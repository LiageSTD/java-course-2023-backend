package edu.java.service.databaseAccess.jooq;

import edu.java.domain.jooq.JooqChatLinkDao;
import edu.java.domain.jooq.JooqLinkDao;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import edu.java.service.databaseAccess.LinkService;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JooqLinkService implements LinkService {
    private final JooqLinkDao jooqLinkDao;
    private final JooqChatLinkDao jooqChatLinkDao;

    @Override
    public Link add(User user, Link link) {
        if (jooqLinkDao.exists(link.getUrl())) {
            Link toAdd = jooqLinkDao.findByUrl(link.getUrl());
            jooqChatLinkDao.add(user.id, toAdd.getId());
            return toAdd;
        } else {
            Long linkId = jooqLinkDao.add(link.getUrl());
            link.setId(linkId);
            jooqChatLinkDao.add(user.id, linkId);
            return link;
        }
    }

    @Override
    public Link remove(User user, Link link) {
        Link linkInDb = findByUrl(link.getUrl());
        if (linkInDb != null) {
            jooqChatLinkDao.remove(user.id, linkInDb.getId());
            if (jooqChatLinkDao.getUserIdsByLink(linkInDb.getId()).length == 0) {
                jooqLinkDao.remove(linkInDb.getUrl());
            }
        }
        return link;
    }

    @Override
    public Link update(User user, Link link) {
        jooqLinkDao.update(link);
        return link;
    }

    @Override
    public List<Link> findAllByUserId(User user) {
        return jooqChatLinkDao.findAllByUserId(user.id);
    }

    @Override
    public Link findByUrl(String url) {
        return jooqLinkDao.findByUrl(url);
    }

    @Override
    public Link findById(User user) {
        return jooqLinkDao.findById(user.getId());
    }

    @Override
    public Collection<Link> listAllByTime(OffsetDateTime updatedAt) {
        return jooqLinkDao.listAllByTime(updatedAt);
    }
}
