package edu.java.domain.jooq;

import edu.java.domain.dao.UsersLinksDao;
import edu.java.domain.jooq.model.tables.records.ChatLinkRecord;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import static edu.java.domain.jooq.model.Tables.CHAT_LINK;
import static edu.java.domain.jooq.model.Tables.LINK;

@Primary
@Repository
@RequiredArgsConstructor
public class JooqChatLinkDao implements UsersLinksDao {
    private final DSLContext dslContext;

    @Override
    public Boolean add(User user, Link link) {
        Long id = 0L;
        if (!dslContext.fetchExists(LINK, LINK.ID.eq(link.getId()))) {
            id = dslContext.insertInto(LINK)
                .set(LINK.URL, link.getUrl())
                .set(LINK.UPDATED_AT, link.getUpdatedAt())
                .set(LINK.UNABLE_TO_UPDATE, link.isUnableToUpdate())
                .returning(LINK.ID)
                .fetchOne()
                .get(LINK.ID);
        } else {
            id = dslContext.select(LINK.ID)
                .from(LINK)
                .where(LINK.URL.eq(link.getUrl()))
                .fetchOne()
                .get(LINK.ID);
        }
        return dslContext.insertInto(CHAT_LINK)
            .set(CHAT_LINK.CHAT_ID, user.getId())
            .set(CHAT_LINK.LINK_ID, id)
            .execute() > 0;
    }

    @Override
    public void remove(User user, Link link) {
        dslContext.deleteFrom(CHAT_LINK)
            .where(CHAT_LINK.CHAT_ID.eq(user.getId()))
            .and(CHAT_LINK.LINK_ID.eq(link.getId()))
            .execute();
    }

    @Override
    public void removeByChat(User user) {
        dslContext.deleteFrom(CHAT_LINK)
            .where(CHAT_LINK.CHAT_ID.eq(user.getId()))
            .execute();
    }

    @Override
    public void removeByLink(Link link) {
        dslContext.deleteFrom(CHAT_LINK)
            .where(CHAT_LINK.LINK_ID.eq(link.getId()))
            .execute();
    }

    @Override
    public List<Link> findAllByUserId(User user) {
        @NotNull Result<Record> records = dslContext.select()
            .from(LINK)
            .join(CHAT_LINK).on(LINK.ID.eq(CHAT_LINK.LINK_ID))
            .where(CHAT_LINK.CHAT_ID.eq(user.getId()))
            .fetch();

        return records.stream()
            .map(dbRecord -> new Link(
                dbRecord.get(LINK.ID),
                dbRecord.get(LINK.URL),
                dbRecord.get(LINK.UPDATED_AT),
                dbRecord.get(LINK.UNABLE_TO_UPDATE)
            ))
            .collect(Collectors.toList());
    }

    @Override
    public long[] getUserIdsByLink(long linkId) {
        @NotNull Result<ChatLinkRecord> records = dslContext.selectFrom(CHAT_LINK)
            .where(CHAT_LINK.LINK_ID.eq(linkId))
            .fetch();
        long[] userIds = new long[records.size()];
        for (int i = 0; i < records.size(); i++) {
            userIds[i] = records.get(i).get(CHAT_LINK.CHAT_ID);
        }
        return userIds;
    }
}
