CREATE DATABASE bank_db;

\c bank_db

CREATE TABLE account (
    id           TEXT PRIMARY KEY,
    account_type TEXT NOT NULL CHECK (account_type IN ('STANDARD', 'PREMIUM', 'GOLD'))
);

CREATE TABLE transaction (
    id               TEXT PRIMARY KEY,
    created_at       TIMESTAMPTZ NOT NULL,
    transaction_type TEXT NOT NULL CHECK (transaction_type IN ('IN', 'OUT')),
    amount           NUMERIC NOT NULL,
    reason           TEXT,
    account_id       TEXT NOT NULL REFERENCES account(id)
);