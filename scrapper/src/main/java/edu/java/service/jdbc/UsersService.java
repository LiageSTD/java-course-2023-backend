package edu.java.service.jdbc;

import edu.java.dto.model.User;
import java.util.List;

public interface UsersService {
    User add(User user);
    void remove(User user);
    boolean update(User user, User newUser);
    List<User> getAll();
}
