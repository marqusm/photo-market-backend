# Photo Market Backend

This is the backend for the Photo Market platform. It is a mock project challenge solution.
The challenge description is in the [Challenge](Challenge.md) file.


## Solution status

1. `X` Design a SQL or NoSql schema for this platform, considering all uses cases
2. `X` Design the API endpoints for the platform
3. `X` Implement an Authentication and Authorization system for the platform (User registration and login)
4. `X` Implement the Entities
5. `X` Implement the API end point to upload a photo
6. `X` Implement the API end point and cache (no libraries please, your own hand-rolled cache) for storing the hashtags of the photos


## Project commands

- Start database
```Shell
docker compose up -d
```

- Stop database
```Shell
docker compose down
```

## Database

Database design is defined in the file [database.sql](docs/database.sql). Details are below, in the diagram. Postgres has been chosen as the database engine, since the entities are well-structured.

```mermaid
erDiagram
    account {
        UUID id PK
        TEXT username UK
        TEXT password_hash
        TEXT role
        TEXT name
    }

    file {
        UUID id PK
        BIGINT size
        BYTEA content
    }

    photo {
        UUID id PK
        UUID account_id FK
        UUID file_id FK,UK
        TEXT name
        TEXT displayable_status
        TEXT purchasable_status
    }

    hashtag {
        UUID photo_id PK,FK
        TEXT name PK
    }

    soc_like {
        UUID account_id PK,FK
        UUID photo_id PK,FK
    }

    transaction {
        UUID id PK
        UUID seller_account_id FK
        UUID buyer_account_id FK
        UUID photo_id FK
        TEXT external_transaction_id
        BIGINT amount
        TEXT currency
        TEXT buyer_name
        TEXT buyer_address
        TEXT buyer_email
        TIMESTAMP created_at
        TEXT status
    }

    account ||--|{ photo : owns
    file ||--|| photo : is_file_for
    photo ||--|{ hashtag : tagged_with
    account ||--|{ soc_like : likes
    photo ||--|{ soc_like : liked_by
    account ||--|{ transaction : sells
    account ||--|{ transaction : buys
    photo ||--|{ transaction : transacted_as

