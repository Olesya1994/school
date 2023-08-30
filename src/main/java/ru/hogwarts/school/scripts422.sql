CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    age INTEGER,
    license BOOLEAN,
    name TEXT
    cars_id INTEGER REFERENCES cars (id),
    );
CREATE TABLE cars (
    Id SERIAL PRIMARY KEY,
    model TEXT,
    brand TEXT,
    price NUMERIC(9,2)
    );