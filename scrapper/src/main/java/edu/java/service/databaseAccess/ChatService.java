package edu.java.service.databaseAccess;

import edu.java.dto.model.User;
import java.util.List;

public interface ChatService {
    User add(User user);

    void remove(User user);

    List<User> getAll();

    long[] getUsersByLink(long linkId);

    boolean exists(Long chatId);
}
