package edu.java.domain.jooq;

import edu.java.domain.daoModel.ChatDao;
import edu.java.dto.model.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import static edu.java.domain.jooq.model.Tables.CHAT;

@Primary
@Repository
@RequiredArgsConstructor
public class JooqChatDao implements ChatDao {
    private final DSLContext dslContext;

    @Override
    public boolean add(Long userId) {
        return dslContext.insertInto(CHAT, CHAT.ID)
            .values(userId)
            .execute() > 0;
    }

    @Override
    public void remove(Long userId) {
        dslContext.deleteFrom(CHAT)
            .where(CHAT.ID.eq(userId))
            .execute();
    }

    @Override
    public boolean update(User user, User newUser) {
        return dslContext.update(CHAT)
            .set(CHAT.ID, newUser.getId())
            .where(CHAT.ID.eq(user.getId()))
            .execute() > 0;
    }

    @Override
    public List<@NotNull User> getAll() {
        return dslContext.selectFrom(CHAT)
            .fetch()
            .map(recordS -> new User(recordS.get(CHAT.ID)));
    }

    @Override
    public boolean exists(User user) {
        return dslContext.fetchExists(
            dslContext.selectFrom(CHAT)
                .where(CHAT.ID.eq(user.getId()))
        );
    }
}
