--liquibase formatted sql

--changeset liage:01
CREATE TABLE IF NOT EXISTS chat
(
    id BIGINT NOT NULL PRIMARY KEY
);
