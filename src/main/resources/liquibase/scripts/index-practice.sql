-- liquibase formatted sql

-- changeset olesyalyahor:1
--CREATE TABLE faculties (
-- id SERIAL PRIMARY KEY,
-- name TEXT,
-- color TEXT,
-- CONSTRAINT login_pass_unique UNIQUE ( name, color)
--  );
--CREATE TABLE students (
--    id SERIAL PRIMARY KEY ,
--    faculty_id INTEGER REFERENCES faculties (id),
--    age INTEGER CHECK (age>16) DEFAULT 20,
--    name TEXT NOT NULL UNIQUE,
--    avatar BOOLEAN
-- );

