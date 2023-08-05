package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    Map<Long, Faculty> facultyMap = new HashMap<>();
    private long count = 0;

    public Faculty addFaculty(@NotNull Faculty faculty) {
        faculty.setId(count++);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!facultyMap.containsKey(faculty.getId())) {
            return null;
        }
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return facultyMap.remove(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyMap.values().stream().
                filter(it -> Objects.equals(it.getColor(), color)).
                collect(Collectors.toList());
    }
}
