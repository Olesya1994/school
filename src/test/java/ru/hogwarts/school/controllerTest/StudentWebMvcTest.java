package ru.hogwarts.school.controllerTest;

import org.assertj.core.api.Assertions;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.AvatarController;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
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
    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;
    @MockBean
    private FacultyController facultyController;
    @MockBean
    private AvatarController avatarController;

    @BeforeEach
    void setUp() throws JSONException {
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

        List<Student> listForTest = List.of(testStudent);
        Collection<Student> collectionForTest = listForTest;
        //  when(studentService.addStudent(any(Student.class))).thenReturn(testStudent);

//        when(studentService.deleteStudent(any(Long.class))).thenReturn(ResponseEntity.ok().build());
//
//        when(studentService.getFaculty(any(Long.class))).thenReturn(ResponseEntity.ok(testFaculty));
//        when(studentService.getStudentCount()).thenReturn(1);
//        when(studentService.getAverageAge()).thenReturn(19);
//        when(studentService.getLastStudent()).thenReturn(listForTest);
    }

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

        when(studentService.addStudent(any(Student.class))).thenReturn(testStudent);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jiny"))
                .andExpect(jsonPath("$.age").value(19));
    }

    @Test
    public void editStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Jiny");
        studentObject.put("id", 33);
        studentObject.put("age", 19);


        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        when(studentService.editStudent(any(Student.class))).thenReturn(testStudent);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.id")).value(33l))
                .andExpect(jsonPath(("$.name")).value("Jiny"))
                .andExpect(jsonPath(("$.age")).value(19));
    }

    @Test
    public void findStudentByAgeTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Jiny");
        studentObject.put("id", 33);
        studentObject.put("age", 19);

        Student testStudent = new Student();
        testStudent.setId(33l);
        testStudent.setName("Jiny");
        testStudent.setAge(19);

        List<Student> listForTest = List.of(testStudent);

        when(studentService.findByAgeBetween(1, 100)).thenReturn((Collection<Student>) listForTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/byAgeBetween?min=1&max=100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "MTT");
        facultyObject.put("id", 66l);
        facultyObject.put("color", "Red");

        Student testStudent = new Student();
        testStudent.setId((long) 33);
        testStudent.setName("Jiny");
        testStudent.setAge(19);

        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");


        when(studentService.getFaculty(any(Long.class))).thenReturn(testFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + 33 + "/getFaculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(66l))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.name").value("MTT"));
    }

    @Test
    public void getStudentCount() throws Exception {

//        JSONObject studentObject = new JSONObject();
//        studentObject.put("name", "Jiny");
//        studentObject.put("id", 33);
//        studentObject.put("age", 19);
//
//        Student testStudent = new Student();
//        testStudent.setId(33l);
//        testStudent.setName("Jiny");
//        testStudent.setAge(19);

        when(studentService.getStudentCount()).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/count")
                        //.content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
               // .andExpect(1);
    }

    @Test
    public void getAverageAge() throws Exception {
        when(studentService.getAverageAge()).thenReturn(19);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/averageAge")
                        //.content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
               // .andExpect(19);
    }

    @Test
    public void getLastStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId((long) 33);
        testStudent.setName("Jiny");
        testStudent.setAge(19);
        List<Student> listForTest = List.of(testStudent);
        when(studentService.getLastStudent()).thenReturn(listForTest);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/last")
                     //   .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
              //  .andExpect((ResultMatcher) listForTest);
    }

}
