CREATE TABLE IF NOT EXISTS transactions(
    id TEXT PRIMARY KEY NOT NULL,
    amount INTEGER NOT NULL,
    date TEXT NOT NULL,
    description TEXT
)