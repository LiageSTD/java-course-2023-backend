/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.model.tables.records;

import edu.java.domain.jooq.model.tables.Link;
import jakarta.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class LinkRecord extends UpdatableRecordImpl<LinkRecord>
    implements Record4<Long, String, OffsetDateTime, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached LinkRecord
     */
    public LinkRecord() {
        super(Link.LINK);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    @ConstructorProperties({"id", "url", "updatedAt", "unableToUpdate"})
    public LinkRecord(
        @Nullable Long id,
        @NotNull String url,
        @NotNull OffsetDateTime updatedAt,
        @Nullable Boolean unableToUpdate
    ) {
        super(Link.LINK);

        setId(id);
        setUrl(url);
        setUpdatedAt(updatedAt);
        setUnableToUpdate(unableToUpdate);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    public LinkRecord(edu.java.domain.jooq.model.tables.pojos.Link value) {
        super(Link.LINK);

        if (value != null) {
            setId(value.getId());
            setUrl(value.getUrl());
            setUpdatedAt(value.getUpdatedAt());
            setUnableToUpdate(value.getUnableToUpdate());
            resetChangedOnNotNull();
        }
    }

    /**
     * Getter for <code>LINK.ID</code>.
     */
    @Nullable
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LINK.ID</code>.
     */
    public void setId(@Nullable Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>LINK.URL</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 1000000000)
    @NotNull
    public String getUrl() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LINK.URL</code>.
     */
    public void setUrl(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LINK.UPDATED_AT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public OffsetDateTime getUpdatedAt() {
        return (OffsetDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>LINK.UPDATED_AT</code>.
     */
    public void setUpdatedAt(@NotNull OffsetDateTime value) {
        set(2, value);
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>LINK.UNABLE_TO_UPDATE</code>.
     */
    @Nullable
    public Boolean getUnableToUpdate() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>LINK.UNABLE_TO_UPDATE</code>.
     */
    public void setUnableToUpdate(@Nullable Boolean value) {
        set(3, value);
    }

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    @Override
    @NotNull
    public Row4<Long, String, OffsetDateTime, Boolean> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row4<Long, String, OffsetDateTime, Boolean> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return Link.LINK.ID;
    }

    @Override
    @NotNull
    public Field<String> field2() {
        return Link.LINK.URL;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field3() {
        return Link.LINK.UPDATED_AT;
    }

    @Override
    @NotNull
    public Field<Boolean> field4() {
        return Link.LINK.UNABLE_TO_UPDATE;
    }

    @Override
    @Nullable
    public Long component1() {
        return getId();
    }

    @Override
    @NotNull
    public String component2() {
        return getUrl();
    }

    @Override
    @NotNull
    public OffsetDateTime component3() {
        return getUpdatedAt();
    }

    @Override
    @Nullable
    public Boolean component4() {
        return getUnableToUpdate();
    }

    @Override
    @Nullable
    public Long value1() {
        return getId();
    }

    @Override
    @NotNull
    public String value2() {
        return getUrl();
    }

    @Override
    @NotNull
    public OffsetDateTime value3() {
        return getUpdatedAt();
    }

    @Override
    @Nullable
    public Boolean value4() {
        return getUnableToUpdate();
    }

    @Override
    @NotNull
    public LinkRecord value1(@Nullable Long value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value2(@NotNull String value) {
        setUrl(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public LinkRecord value3(@NotNull OffsetDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value4(@Nullable Boolean value) {
        setUnableToUpdate(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord values(
        @Nullable Long value1,
        @NotNull String value2,
        @NotNull OffsetDateTime value3,
        @Nullable Boolean value4
    ) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }
}
