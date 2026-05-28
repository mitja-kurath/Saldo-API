ALTER TABLE transactions ADD COLUMN account_id TEXT REFERENCES accounts(account_id);
