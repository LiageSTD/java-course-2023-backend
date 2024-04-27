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
    TIMESTAMPTZ
    NOT
    NULL,
    unable_to_update
    BOOLEAN
);
