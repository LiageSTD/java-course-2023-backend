package edu.java.service.jdbc;

import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import java.util.List;

public interface LinksService {
    Link add(Link link);
    Link remove(Link link);
    Link update(Link link);
    List<Link> findAllByUserId(User user);
    Link findById(User user);
}
