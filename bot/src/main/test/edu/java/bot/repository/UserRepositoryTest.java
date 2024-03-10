package edu.java.bot.repository;

import edu.java.bot.service.linksHandler.links.GitHubLink;
import edu.java.bot.utils.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {
    Long id = 123L;

    @Test
    void putGetUserTest() {
        User user = new User(id, new ArrayList<>());
        UserRepository userRepository = new UserRepository();
        userRepository.putUser(id, user);
        Assertions.assertEquals(
            user,
            userRepository.getUser(id)
        );
    }

    @Test
    void addLinkToAUser() {
        User user = new User(id, new ArrayList<>());
        UserRepository userRepository = new UserRepository();
        userRepository.putUser(id, user);
        GitHubLink link = new GitHubLink("github.com", "lol");
        userRepository.addLinkToAUser(id, link);
        Assertions.assertEquals(userRepository.getUser(id).linksToTrack, List.of(link));
    }

    @Test
    void addLinkToANullUser() {
        UserRepository userRepository = new UserRepository();
        GitHubLink link = new GitHubLink("github.com", "lol");
        userRepository.addLinkToAUser(id, link);
        Assertions.assertEquals(userRepository.getUser(id).linksToTrack, List.of(link));
    }

    @Test
    void removeUser() {
        UserRepository userRepository = new UserRepository();
        User user = new User(id, new ArrayList<>());
        userRepository.putUser(id, user);
        userRepository.removeUser(id);
        Assertions.assertNull(userRepository.getUser(id));
    }
}
