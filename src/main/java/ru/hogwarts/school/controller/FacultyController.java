package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private FacultyService facultyService = new FacultyService();

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getStudentInfo(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping()
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        facultyService.addFaculty(faculty);
        return faculty;
    }

    @PutMapping()
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty, @PathVariable long id) {
        Faculty faculty1 = facultyService.editFaculty(id, faculty);
        if (faculty1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(long id) {
        Faculty faculty1 = facultyService.deleteFaculty(id);
        if (faculty1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

}
