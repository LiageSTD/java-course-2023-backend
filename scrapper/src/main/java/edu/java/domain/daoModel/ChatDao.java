package edu.java.domain.daoModel;

import edu.java.dto.model.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface ChatDao {
    boolean add(Long userId);

    void remove(Long userId);

    boolean update(User user, User newUser);

    List<@NotNull User> getAll();

    boolean exists(User user);
}
