CREATE TABLE IF NOT EXISTS cities (
    id SERIAL PRIMARY KEY,
    code varchar(3) NOT NULL,
    name varchar(255) NOT NULL
);