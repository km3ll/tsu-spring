CREATE TABLE IF NOT EXISTS countries (
    country_id SERIAL PRIMARY KEY,
    country_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cities (
    city_id SERIAL PRIMARY KEY,
    city_code VARCHAR(3) NOT NULL,
    city_name VARCHAR(255) NOT NULL,
    country_id INTEGER NOT NULL
);