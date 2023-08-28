CREATE TABLE users (
    Id SERIAL PRIMARY KEY  REFERENCES cars (id),
    Age INTEGER,
    license BOOLEAN,
    name TEXT
    );
CREATE TABLE cars (
    Id SERIAL PRIMARY KEY REFERENCES users (id),
    model TEXT,
    brand TEXT,
    price NUMERIC(9,2)
    );