CREATE TABLE IF NOT EXISTS cities (
    id SERIAL PRIMARY KEY,
    city_code varchar(3) NOT NULL,
    city_name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS countries (
    id SERIAL PRIMARY KEY,
    country_name varchar(255) NOT NULL
);