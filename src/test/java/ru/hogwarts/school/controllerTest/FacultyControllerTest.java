package ru.hogwarts.school.controllerTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.FacultyController;
@SpringBootTest
public class FacultyControllerTest {
    private MockMvc mockMvc;

    private int port;
    @MockBean
    private FacultyController controller;
    @MockBean
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoad() {
        Assertions.assertThat(controller).isNotNull();

    }
}
