--liquibase formatted sql

--changeset liage:03
CREATE TABLE IF NOT EXISTS user_link
(
    chat_id BIGINT REFERENCES chat
        (
         id
            ),

    link_id BIGINT REFERENCES link
        (
         id
            ),
    CONSTRAINT users_links_pk PRIMARY KEY
        (
         chat_id,
         link_id
            )
);
