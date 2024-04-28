--liquibase formatted sql

--changeset liage:02
CREATE TABLE IF NOT EXISTS link
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    url TEXT NOT NULL UNIQUE,
    updated_at TIMESTAMPTZ NOT NULL,
    unable_to_update BOOLEAN
);
