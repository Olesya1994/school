package ru.hogwarts.school.controllerTest;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository repository;
    @SpyBean
    private StudentService service;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void createStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Jiny");
        studentObject.put("id", 33);
        studentObject.put("age", 19);

        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);

        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");
        testStudent.setFaculty(testFaculty);

        List<Student> l = List.of(testStudent);
        Collection<Student> c = Collections.unmodifiableList(l);


        when(studentController.createStudent(any(Student.class))).thenReturn(testStudent);
        when(studentController.editStudent(any(Student.class))).thenReturn(ResponseEntity.ok(testStudent));
        when(studentController.deleteStudent(any(Long.class))).thenReturn(ResponseEntity.ok().build());
        when(studentController.findStudentByAge(any(int.class), any(int.class))).thenReturn(ResponseEntity.ok(c));
        when(studentController.getFaculty(any(Long.class))).thenReturn(ResponseEntity.ok(testFaculty));
        when(studentController.getStudentCount()).thenReturn(1);
        when(studentController.getAverageAge()).thenReturn(19);
        when(studentController.getLastStudent()).thenReturn(l);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.id")).value(33l))
                .andExpect(jsonPath(("$.name")).value("Jiny"))
                .andExpect(jsonPath(("$.age")).value(19));
    }

//    @Test
//    public Void editStudent() {
//    }
//
//    @Test
//    public Void findFaculties() {
//    }
//
//    @Test
//    public Void getFaculty() {
//    }
//
//    @Test
//    public Void getStudentCount() {
//    }
//
//    @Test
//    public Void getAverageAge() {
//    }
//
//    @Test
//    public Void getLastStudent() {
//    }


}
