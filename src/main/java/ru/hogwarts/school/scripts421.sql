CREATE TABLE students (
    Id SERIAL PRIMARY KEY ,
    facultyId INTEGER REFERENCES faculties (id),
    age INTEGER CHECK (age>16) DEFAULT 20,
    name TEXT NOT NULL UNIQUE,
    avatar BOOLEAN
 )

CREATE TABLE faculties (
 Id SERIAL PRIMARY KEY,
 name TEXT,
 color TEXT,
 CONSTRAINT login_pass_unique UNIQUE ( name, color)
  )
