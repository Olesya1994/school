package ru.hogwarts.school.controllerTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;


public class StudentControllerTest {
//    private int port;
//    @Autowired
//    private StudentController studentController;
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void contextLoad() throws Exception {
//        Assertions.assertThat(studentController).isNotNull();
//
//    }
//
//    @Test
//    public void getStudentInfoTest() throws Exception {
//        Assertions.assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/student" + "/1", String.class)).isEqualTo(studentController.getStudentInfo(1L));
//
//    }
//    //public Student createFaculty


}
