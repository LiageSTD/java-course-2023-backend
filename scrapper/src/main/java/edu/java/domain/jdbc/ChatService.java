package edu.java.domain.jdbc;

import edu.java.dto.model.User;
import java.util.List;

public interface ChatService {
    User add(User user);

    void remove(User user);

    boolean update(User user, User newUser);

    List<User> getAll();

    long[] getUsersByLink(long linkId);

    boolean exists(Long chatId);
}
