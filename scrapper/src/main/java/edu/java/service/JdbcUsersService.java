package edu.java.service;

import edu.java.domain.dao.UsersDao;
import edu.java.domain.dao.UsersLinksDao;
import edu.java.dto.model.User;
import edu.java.service.jdbc.UsersService;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RequiredArgsConstructor
public class JdbcUsersService implements UsersService {
    private final UsersDao usersDao;
    private final UsersLinksDao usersLinksDao;

    @Override
    public User add(User user) {
        if(usersDao.add(user)) {
            return user;
        }
        return null;
    }

    @Override
    public void remove(User user) {
        usersDao.remove(user);
        usersLinksDao.findAllByUserId(user);
    }

    @Override
    public boolean update(User user, User newUser) {
        return usersDao.update(user, newUser);
    }

    @Override
    public List<User> getAll() {
        return usersDao.getAll();
    }
}
