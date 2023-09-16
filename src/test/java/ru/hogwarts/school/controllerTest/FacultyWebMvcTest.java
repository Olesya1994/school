package ru.hogwarts.school.controllerTest;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.AvatarController;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository repository;
    @MockBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController studentController;
    @MockBean
    private StudentController facultyController;
    @MockBean
    private AvatarController avatarController;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
        Assertions.assertThat(facultyService).isNotNull();
    }

    @Test
    public void getStudentInfoTest() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "MTT");
        facultyObject.put("id", 66l);
        facultyObject.put("color", "Red");

        when(facultyService.findFaculty(any(Long.class))).thenReturn(testFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/66")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("MTT"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test()
    public void createFacultyTest() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "MTT");
        facultyObject.put("id", 66l);
        facultyObject.put("color", "Red");


        when(facultyService.addFaculty(any(Faculty.class))).thenReturn(testFaculty);
        when(facultyService.findFaculty(any(Long.class))).thenReturn(testFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("MTT"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test()
    public void editFacultyTest() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "MTT");
        facultyObject.put("id", 66l);
        facultyObject.put("color", "Red");

        when(facultyService.editFaculty(any(Faculty.class))).thenReturn(testFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("MTT"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    public void deleteFaculty() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "MTT");
        facultyObject.put("id", 66l);
        facultyObject.put("color", "Red");

        when(facultyService.findFaculty(any(Long.class))).thenReturn(testFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/66")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findFaculties() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66L);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "MTT");
        facultyObject.put("id", 66L);
        facultyObject.put("color", "Red");
        List<Faculty> listForTest = List.of(testFaculty);

        when(facultyService.findByColor(any(String.class))).thenReturn(listForTest);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/byColor?color=Red")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findStudents() throws JSONException {
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
    }

    @Test
    public void getLongestFacultyNameTest() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setId(66);
        testFaculty.setName("MTT");
        testFaculty.setColor("Red");

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "MTT");
        facultyObject.put("id", 66l);
        facultyObject.put("color", "Red");
        when(facultyService.getLongestFacultyName()).thenReturn("MTT");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/longestName")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
