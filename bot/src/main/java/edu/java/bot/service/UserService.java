package edu.java.bot.service;

import edu.java.bot.repository.UserRepository;
import edu.java.bot.service.linksHandler.links.Link;
import edu.java.bot.utils.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean containsUserId(Long id) {
        return userRepository.containsUser(id);
    }

    public User findUserById(Long id) {
        return userRepository.getUser(id);
    }

    public void removeLinkFromAUser(Long id, Link link) {
        userRepository.removeLinkFromAUser(id, link);
    }

    public void addLinkToAUser(Long id, Link link) {
        userRepository.addLinkToAUser(id, link);
    }

    public List<Link> getUserLinks(Long id) {
        return userRepository.getUserLinks(id);
    }

    public void addUser(Long id) {
        userRepository.putUser(id, new User(id, new ArrayList<>()));
    }

}
