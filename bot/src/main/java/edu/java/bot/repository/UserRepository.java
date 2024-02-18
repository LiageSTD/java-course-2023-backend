package edu.java.bot.repository;

import edu.java.bot.service.linksHandler.links.Link;
import edu.java.bot.utils.User;
import edu.java.bot.utils.SolvedLink;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<Long, User> datasource = new HashMap<>();

    public void putUser(Long id, User user) {
        datasource.put(id, user);
    }

    public boolean containsUser(Long id) {
        return datasource.containsKey(id);
    }

    public void removeUser(Long id) {
        datasource.remove(id);
    }

    public void removeLinkFromAUser(Long id, Link link) {
        User currentUser = datasource.getOrDefault(id, new User(id, new ArrayList<>()));
        currentUser.removeLinks(link);
        datasource.put(id, currentUser);
    }

    public User getUser(Long id) {
        return datasource.get(id);
    }

    public void addLinkToAUser(Long id, Link link) {
        User currentUser = datasource.getOrDefault(id, new User(id, new ArrayList<>()));
        currentUser.updateLinks(link);
        datasource.put(id, currentUser);
    }

    public List<Link> getUserLinks(Long id) {
        return datasource.get(id).linksToTrack;
    }
}
