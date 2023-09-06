package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty addFaculty(Faculty faculty) {
        logger.info("method addFaculty was invoked");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("method findFaculty was invoked");
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("method editFaculty was invoked");
        if (!facultyRepository.existsById(faculty.getId())) {
            logger.info("Faculty with Id already exists");
            return null;
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("method deleteFaculty was invoked");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("method findByColor was invoked");
        return facultyRepository.findByColor(color);
    }

    public Collection<Student> findStudents(long id) {

        logger.info("method findStudents was invoked");
        return findFaculty(id).getStudents();
    }

    public String getLongestFacultyName() {
        return facultyRepository.findAll().
                stream().
                map(Faculty::getName).
                max(Comparator.naturalOrder()).orElse(null);


    }

    public int test() {
        return IntStream.rangeClosed(1, 1000000).parallel().sum();
    }
}
