package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService service) {
        this.studentService = service;
    }

    @GetMapping("{id}")

    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping()
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping()
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student student1 = studentService.editStudent(student);
        if (student1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.findStudent(id) == (null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byAgeBetween")
    public ResponseEntity<Collection<Student>> findStudentByAge(@RequestParam int min, int max) {
        if (min > max) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("{studentId}/getFaculty")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long studentId) {
//        if (studentService.findStudent(studentId) == (null)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
        return ResponseEntity.ok(studentService.getFaculty(studentId));
    }

    @GetMapping("/count")
    public int getStudentCount() {
        return studentService.getStudentCount();
    }

    @GetMapping("/averageAge")
    public int getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/last")
    public List<Student> getLastStudent() {
        return studentService.getLastStudent();
    }

    @GetMapping("/firstA")
    public List<String> getStudentWithFirstA() {
        return studentService.getStudentWithFirstA();
    }

    @GetMapping ("/printAll")
    public void printAll() {
        studentService.printAllStudents();
    }

    @GetMapping ("/printS")
    public void printS() {
        studentService.print6Student();
    }
}
