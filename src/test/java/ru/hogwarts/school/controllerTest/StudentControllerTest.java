package ru.hogwarts.school.controllerTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;

public class StudentControllerTest {
    @Value("${server.port}")
    private int port;
    @Autowired
    private StudentController controller;
    @Autowired
    private TestRestTemplate restTemplate;
//    @Test
//    public void contextLoad() {
//        Assertions.assertThat(controller).isNotNull();
//    }
    @Test
    public void  getFacultyTest(){
        restTemplate.postForEntity()
       restTemplate.getForEntity(
                        "http://localhost:"+port+ "/student/2/getFaculty/", Faculty.class);
    }
}
