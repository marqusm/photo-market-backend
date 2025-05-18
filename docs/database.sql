CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE account
(
    id            UUID PRIMARY KEY NOT NULL DEFAULT uuid_generate_v4(),
    username      TEXT             NOT NULL UNIQUE,
    password_hash TEXT             NOT NULL,
    role          TEXT             NOT NULL,
    name          TEXT             NOT NULL
);

CREATE TABLE file
(
    id      UUID PRIMARY KEY NOT NULL DEFAULT uuid_generate_v4(),
    size    BIGINT           NOT NULL,
    content BYTEA            --NOT NULL
);

CREATE TABLE photo
(
    id                 UUID PRIMARY KEY NOT NULL DEFAULT uuid_generate_v4(),
    account_id         UUID             NOT NULL REFERENCES account (id),
    file_id            UUID             NOT NULL UNIQUE REFERENCES file (id),
    name               TEXT             NOT NULL,
    displayable_status TEXT             NOT NULL,
    purchasable_status TEXT             NOT NULL
);

CREATE TABLE hashtag
(
    photo_id UUID NOT NULL REFERENCES photo (id),
    name     TEXT NOT NULL,
    PRIMARY KEY (photo_id, name)
);

CREATE TABLE soc_like
(
    account_id UUID NOT NULL REFERENCES account (id),
    photo_id   UUID NOT NULL REFERENCES photo (id),
    PRIMARY KEY (account_id, photo_id)
);

CREATE TABLE transaction
(
    id                      UUID PRIMARY KEY NOT NULL DEFAULT uuid_generate_v4(),
    seller_account_id       UUID             NOT NULL REFERENCES account (id),
    buyer_account_id        UUID             NOT NULL REFERENCES account (id),
    photo_id                UUID             NOT NULL REFERENCES photo (id),
    external_transaction_id TEXT,
    amount                  BIGINT           NOT NULL,
    currency                TEXT             NOT NULL,
    buyer_name              TEXT             NOT NULL,
    buyer_address           TEXT             NOT NULL,
    buyer_email             TEXT             NOT NULL,
    created_at              TIMESTAMP,
    status                  TEXT             NOT NULL
);
