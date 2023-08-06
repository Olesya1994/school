package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

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
    public Student createFaculty(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping()
    public ResponseEntity<Student> editFaculty(@RequestBody Student student) {
        Student student1 = studentService.editStudent(student);
        if (student1 == null) {
            //return ResponseEntity.ok(student1);
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

//    @GetMapping("/byAge")
//    public ResponseEntity<Collection<Student>> findFaculties(@RequestParam(required = false) int age) {
//        if (age > 0) {
//            return ResponseEntity.ok(studentService.findByAge(age));
//        }
//        return ResponseEntity.ok(Collections.emptyList());
//    }

}
