package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Student editStudent(Student student){
        if (!studentMap.containsKey(student.getId())){
            return null;
        }
        studentMap.put( student.getId(), student);
        return student;
    }
    public Student deleteStudent(long id){
        return studentMap.remove(id);
    }
    public Collection<Student> findByAge(int age) {
        return studentMap.values().stream().
                filter(it ->it.getAge() == age).
                collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "StudentService{" +
                "studentMap=" + studentMap +
                ", count=" + count +
                '}';
    }
}
