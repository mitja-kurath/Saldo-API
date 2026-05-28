CREATE TABLE IF NOT EXISTS transactions(
                                           id UUID PRIMARY KEY NOT NULL,
                                           amount INTEGER NOT NULL,
                                           date DATE NOT NULL,
                                           description TEXT
)