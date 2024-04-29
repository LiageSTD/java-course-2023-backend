package edu.java.service.databaseAccess.jooq;

import edu.java.domain.jooq.JooqChatDao;
import edu.java.domain.jooq.JooqChatLinkDao;
import edu.java.dto.model.User;
import edu.java.service.databaseAccess.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JooqChatService implements ChatService {

    private final JooqChatDao jooqChatDao;
    private final JooqChatLinkDao jooqChatLinkDao;

    @Override
    public User add(User user) {
        return jooqChatDao.add(user.id) ? user : null;
    }

    @Override
    public void remove(User user) {
        jooqChatDao.remove(user.id);
    }

    @Override
    public List<User> getAll() {
        return jooqChatDao.getAll();
    }

    @Override
    public long[] getUsersByLink(long linkId) {
        return jooqChatLinkDao.getUserIdsByLink(linkId);
    }

    @Override
    public boolean exists(Long chatId) {
        return jooqChatDao.exists(new User(chatId));
    }
}
