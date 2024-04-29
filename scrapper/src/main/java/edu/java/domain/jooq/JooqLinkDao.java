package edu.java.domain.jooq;

import edu.java.domain.daoModel.LinksDao;
import edu.java.domain.jooq.model.tables.records.LinkRecord;
import edu.java.dto.model.Link;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import static edu.java.domain.jooq.model.Tables.LINK;

@Primary
@Repository
@RequiredArgsConstructor
public class JooqLinkDao implements LinksDao {
    private final DSLContext dslContext;

    @Override
    public Long add(String url) {
        return dslContext.insertInto(LINK, LINK.URL, LINK.UPDATED_AT, LINK.UNABLE_TO_UPDATE)
            .values(url, OffsetDateTime.now(), false)
            .returning(LINK.ID)
            .fetchOne()
            .getId();
    }

    @Override
    public void remove(String url) {
        dslContext.deleteFrom(LINK)
            .where(LINK.URL.eq(url))
            .execute();
    }

    @Override
    public List<@NotNull Link> getAll() {
        return dslContext.selectFrom(LINK)
            .fetch()
            .map(recordS -> new Link(
                recordS.get(LINK.ID),
                recordS.get(LINK.URL),
                recordS.get(LINK.UPDATED_AT),
                recordS.get(LINK.UNABLE_TO_UPDATE)
            ));
    }

    @Nullable
    @Override
    public Link findById(Long id) {
        return dslContext.selectFrom(LINK)
            .where(LINK.ID.eq(id))
            .fetchOptional()
            .map(recordS -> new Link(
                recordS.get(LINK.ID),
                recordS.get(LINK.URL),
                recordS.get(LINK.UPDATED_AT),
                recordS.get(LINK.UNABLE_TO_UPDATE)
            ))
            .orElse(null);
    }

    @Nullable
    @Override
    public Link findByUrl(String url) {
        return dslContext.selectFrom(LINK)
            .where(LINK.URL.eq(url))
            .fetchOptional()
            .map(recordS -> new Link(
                recordS.get(LINK.ID),
                recordS.get(LINK.URL),
                recordS.get(LINK.UPDATED_AT),
                recordS.get(LINK.UNABLE_TO_UPDATE)
            ))
            .orElse(null);
    }

    @Override
    public void update(Link link) {
        dslContext.update(LINK)
            .set(LINK.URL, link.getUrl())
            .set(LINK.UPDATED_AT, link.getUpdatedAt())
            .set(LINK.UNABLE_TO_UPDATE, link.isUnableToUpdate())
            .where(LINK.URL.eq(link.getUrl()))
            .execute();
    }

    @Nullable
    @Override
    public Collection<Link> listAllByTime(OffsetDateTime updatedAt) {
        @NotNull Result<LinkRecord> records = dslContext.selectFrom(LINK)
            .where(LINK.UPDATED_AT.lessThan(updatedAt))
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
    public boolean exists(String url) {
        return dslContext.fetchExists(
            dslContext.selectOne()
                .from(LINK)
                .where(LINK.URL.eq(url))
        );
    }
}
