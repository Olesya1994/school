package ru.hogwarts.school.controllerTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    public void getFacultyInfoTest() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66L);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, Faculty.class);

        assertNotNull(this.restTemplate.getForEntity(
                "http://localhost:" + port + "/faculty/66", Faculty.class));
    }

    @Test()
    public void createFacultyTest() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        assertNotNull(this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, Faculty.class));
        Assertions.assertThat(this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, Faculty.class)).isEqualTo(testFaculty);

    }

    @Test()
    public void editFacultyTest() {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(33);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");
        this.restTemplate.put("http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        Assertions.assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/faculty/" + testFaculty.getId(), Faculty.class).toString()).isEqualTo(testFaculty.toString());
    }


    @Test
    public void deleteFaculty() {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, Faculty.class);

        this.restTemplate.delete("http://localhost:" + port + "/faculty/" + testFaculty.getId());
        Assertions.assertThat(facultyController.getFacultyInfo(testFaculty.getId())).isEqualTo(ResponseEntity.notFound().build());
    }

    @Test
    public void findFaculties() {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66L);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");
        List<Faculty> listForTest = List.of(testFaculty);
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        assertNotNull(this.restTemplate.getForObject(
                "http://localhost:" + port + "/faculty/byColor?color=Red", List.class));

    }

    @Test
    public void findStudents() {

        Student testStudent = new Student();
        testStudent.setId(33L);
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
                "http://localhost:" + port + "/faculty/66/allStudens", Student.class));
    }

    @Test
    public void getLongestFacultyNameTest() {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", testFaculty, String.class);

        assertNotNull(this.restTemplate.getForObject(
                "http://localhost:" + port + "/faculty/longestName", String.class));
    }
}
