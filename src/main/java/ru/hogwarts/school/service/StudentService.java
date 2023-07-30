package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
   private long count =0;
    public Student addStudent(@NotNull Student student){
        student.setId(count++);
        studentMap.put(student.getId(), student);
        return student;
    }
    public Student findStudent(long id){
       return studentMap.get(id);
    }
    public Student editStudent(long id,Student student){
        if (!studentMap.containsKey(id)){
            return null;
        }
        studentMap.put(student.getId(), student);
        return student;
    }
    public Student deleteStudent(long id){
        Student student = studentMap.remove(id);
        return student;
    }

}
