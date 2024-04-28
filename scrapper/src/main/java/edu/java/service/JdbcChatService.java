package edu.java.service;

import edu.java.domain.dao.UsersDao;
import edu.java.domain.dao.UsersLinksDao;
import edu.java.domain.jdbc.ChatService;
import edu.java.dto.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {
    private final UsersDao usersDao;
    private final UsersLinksDao usersLinksDao;

    @Override
    public User add(User user) {
        if (usersDao.add(user)) {
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

    @Override
    public long[] getUsersByLink(long linkId) {
        return usersLinksDao.getUserIdsByLink(linkId);
    }

    @Override
    public boolean exists(Long chatId) {
        return usersDao.exists(new User(chatId));
    }
}
