package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService = new StudentService();

    public StudentController(StudentService service) {
        this.studentService = service;
    }

    @GetMapping("{id}")

    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping()
    public Student createFaculty(@RequestBody Student student) {
        studentService.addStudent(student);
        return student;
    }

    @PutMapping()
    public ResponseEntity<Student> editFaculty(@RequestBody Student student, @PathVariable long id) {
        Student student1 = studentService.editStudent(id, student);
        if (student1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(long id) {
        Student student1 = studentService.deleteStudent(id);
        if (student1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }
}
