package edu.java.bot.utils;

import edu.java.bot.service.linksHandler.links.Link;
import java.util.List;

public class User {
    public Long id;
    public List<Link> linksToTrack;

    public User(Long userId, List<Link> links) {
        id = userId;
        linksToTrack = links;
    }

    public void updateLinks(List<Link> arrToAdd) {
        linksToTrack.addAll(arrToAdd);
    }
    public void updateLinks(Link link) {
        linksToTrack.add(link);
    }
    public void removeLinks(List<Link> arrToAdd) {
        linksToTrack.removeAll(arrToAdd);
    }
    public void removeLinks(Link link) {
        linksToTrack.remove(link);
    }
    public String getAllLinks() {
        StringBuilder st = new StringBuilder();
        for (Link el : linksToTrack) {
            st.append(el.host()).append(el.path()).append("\n");
        }
        return !st.isEmpty() ? st.toString() : "You don't have any links to track";
    }


}
