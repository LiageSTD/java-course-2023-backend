--liquibase formatted sql

--changeset liage:03
CREATE TABLE IF NOT EXISTS users_links
(
    chat_id BIGINT REFERENCES users
(
    id
),

    link_id BIGINT REFERENCES links
(
    id
),
    CONSTRAINT users_links_pk PRIMARY KEY
(
    chat_id,
    link_id
)
    );
