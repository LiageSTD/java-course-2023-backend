--liquibase formatted sql

--changeset liage:03
CREATE TABLE IF NOT EXISTS chat_link
(
    chat_id BIGINT REFERENCES chat
        (
         id
            ),

    link_id BIGINT REFERENCES link
        (
         id
            ),
    CONSTRAINT chat_link_pk PRIMARY KEY
        (
         chat_id,
         link_id
            )
);
