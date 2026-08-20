\c bank_db

INSERT INTO account (id, account_type) VALUES ('001', 'STANDARD');
INSERT INTO account (id, account_type) VALUES ('002', 'PREMIUM');

INSERT INTO transaction (id, created_at, transaction_type, amount, reason, account_id)
VALUES ('001', '2026-08-20T10:00:00Z', 'IN', 100, 'depot', '001');
INSERT INTO transaction (id, created_at, transaction_type, amount, reason, account_id)
VALUES ('002', '2026-08-20T11:00:00Z', 'OUT', 30, 'retrait', '001');