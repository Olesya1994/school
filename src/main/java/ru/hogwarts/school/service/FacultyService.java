package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

//    public FacultyService(FacultyRepository facultyRepository) {
//        this.facultyRepository = facultyRepository;
//    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!facultyRepository.existsById(faculty.getId())) {
            return null;
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return (Collection<Faculty>) facultyRepository.findAll().stream().
                filter(it -> it.getColor().equals(color));
    }
}
