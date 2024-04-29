package edu.java.service.databaseAccess.jpa;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.domain.jpa.model.Chat;
import edu.java.dto.model.User;
import edu.java.service.databaseAccess.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaChatService implements ChatService {

    private final JpaChatDao chatDao;

    @Override
    public User add(User user) {
        Chat userInDb = chatDao.findById(user.id).orElse(null);
        if (userInDb == null) {
            chatDao.save(new Chat(user.id, null));
        }
        return user;
    }

    @Override
    public void remove(User user) {
        chatDao.deleteById(user.id);
    }

    @Override
    public List<User> getAll() {
        return chatDao.findAll().stream().map(chat -> new User(chat.getId())).toList();
    }

    @Override
    public long[] getUsersByLink(long linkId) {
        return chatDao.findAll().stream()
            .filter(chat -> chat.getLinks().stream().anyMatch(link -> link.getId() == linkId))
            .mapToLong(Chat::getId)
            .toArray();
    }

    @Override
    public boolean exists(Long chatId) {
        return chatDao.existsById(chatId);
    }
}
