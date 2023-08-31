-- liquibase formatted sql
-- changeset Olesya:1
CREATE INDEX student_name_idx on student (name)
-- changeset Olesya:2
CREATE INDEX faculty_color_name_idx on faculty (color, name)