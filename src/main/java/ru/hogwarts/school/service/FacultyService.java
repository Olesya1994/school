package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

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

    public Faculty editFaculty(long id, Faculty faculty) {
        if (!facultyMap.containsKey(id)) {
            return null;
        }
        facultyMap.put(id, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = facultyMap.remove(id);
        return faculty;
    }
}
