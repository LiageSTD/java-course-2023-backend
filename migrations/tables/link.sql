--liquibase formatted sql

--changeset liage:02
CREATE TABLE IF NOT EXISTS link
(
    id
        BIGSERIAL
        PRIMARY
            KEY,
    url
        TEXT
        NOT
            NULL
        UNIQUE,
    updated_at
        timestamp WITH TIME ZONE
        NOT
            NULL,
    unable_to_update
        BOOLEAN
);
