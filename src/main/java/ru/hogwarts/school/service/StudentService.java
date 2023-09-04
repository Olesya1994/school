package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Value("${avatars.dir}")
    private String avatarsDir;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AvatarRepository avatarRepository;

    public Student addStudent(Student student) {
        logger.info("method addStudent was invoked");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("method findStudent was invoked");
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("method editStudent was invoked");
        if (!studentRepository.existsById(student.getId())) {
            logger.info("Student with Id already exists");
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long stId) {
        logger.info("method deleteStudent was invoked");
        studentRepository.deleteById(stId);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("method findByAgeBetween was invoked");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFaculty(Long studentId) {
        logger.info("method getFaculty was invoked");
        return findStudent(studentId).getFaculty();
    }
    public Avatar findAvatar(long studentId) {
        logger.info("method findAvatar was invoked");
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    private String getExtension(String fileName) {

        logger.info("method getExtension was invoked");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    public int getStudentCount(){
        logger.info("method getStudentCount was invoked");
        return studentRepository.getStudentCount();
    };
    public int getAverageAge(){
        logger.info("method getAverageAge was invoked");
        return studentRepository.getAverageAge();    };

    public List<Student> getLastStudent(){

        logger.info("method getLastStudent was invoked");
        return studentRepository.getLastStudent();
    };

}
