package ru.hogwarts.school.controllerTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void createStudentTest() {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        Assertions.assertThat(this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class)).isNotNull();
    }

    @Test
    void getStudentInfoTest() {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);
        assertNotNull(this.restTemplate.getForEntity(
                "http://localhost:" + port + "/student/33", Student.class));
        System.out.println(testStudent);
    }

    @Test
    void deleteStudentTest() {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);
        this.restTemplate.delete("http://localhost:" + port + "/student/" + testStudent.getId());
        Assertions.assertThat(studentController.getStudentInfo(testStudent.getId())).isEqualTo(ResponseEntity.notFound().build());
    }

    @Test
    public void editStudentTest() {
        Student testStudent = new Student();
        testStudent.setId(55L);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        this.restTemplate.put("http://localhost:" + port + "/student", testStudent, Student.class);
        Assertions.assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/student/" + testStudent.getId(), Student.class)).isEqualTo((testStudent));
    }

    @Test
    public void findStudentByAgeTest() {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);
        assertNotNull(this.restTemplate.getForObject(
                "http://localhost:" + port + "/student/byAgeBetween?min=1&max=100", String.class));
    }

    @Test
    public void getStudentCountTest() {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);
        assertNotNull(this.restTemplate.getForObject(
                "http://localhost:" + port + "/student/count", Integer.class));

    }

    @Test
    public void getFacultyTest() {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);

        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");
        testStudent.setFaculty(testFaculty);

        this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);

        assertNotNull(this.restTemplate.getForEntity(
                "http://localhost:" + port + "/student/33/getFaculty", Faculty.class));

    }

    @Test
    public void getAverageAge() {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);
        assertNotNull(this.restTemplate.getForObject(
                "http://localhost:" + port + "/student/averageAge", Integer.class));

    }

    @Test
    public void getLastStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", testStudent, Student.class);
        assertNotNull(this.restTemplate.getForObject(
                "http://localhost:" + port + "/student/last", Collection.class));

    }

}
