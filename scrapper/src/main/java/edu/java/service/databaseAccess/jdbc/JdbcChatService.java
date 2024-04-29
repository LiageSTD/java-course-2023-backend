package edu.java.service.databaseAccess.jdbc;

import edu.java.domain.daoModel.UsersLinksDao;
import edu.java.domain.jdbc.JdbcChatDao;
import edu.java.service.databaseAccess.ChatService;
import edu.java.dto.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {
    private final JdbcChatDao chatDao;
    private final UsersLinksDao usersLinksDao;

    @Override
    public User add(User user) {
        if (chatDao.add(user.id)) {
            return user;
        }
        return null;
    }

    @Override
    public void remove(User user) {
        chatDao.remove(user.id);
    }


    @Override
    public List<User> getAll() {
        return chatDao.getAll();
    }

    @Override
    public long[] getUsersByLink(long linkId) {
        return usersLinksDao.getUserIdsByLink(linkId);
    }

    @Override
    public boolean exists(Long chatId) {
        return chatDao.exists(new User(chatId));
    }
}
