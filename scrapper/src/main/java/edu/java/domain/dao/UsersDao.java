package edu.java.domain.dao;

import edu.java.dto.model.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface UsersDao {
    boolean add(User user);

    void remove(User user);

    boolean update(User user, User newUser);

    List<@NotNull User> getAll();
}
