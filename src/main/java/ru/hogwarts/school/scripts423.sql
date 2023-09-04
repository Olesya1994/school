SELECT students.name, students.age, faculty.id
FROM students
LEFT JOIN faculties ON students.facultyId = faculty.id

SELECT students.name, students.age, students.avatar, faculty.id
FROM students
INNER JOIN faculties ON students.facultyId = faculty.id