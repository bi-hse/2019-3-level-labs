package hse.lab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    private final String TEST_URL = "http://localhost:8080/";

    @Autowired
    private MockMvc mvc;

    @Test
    public void urlIsAccessible() throws Exception {
        this.mvc.perform(get("/news")).andExpect(status().isOk());
    }

    @Test
    public void tprogerIsAccessible() throws Exception {
        this.mvc.perform(get("/pageTitle"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tproger — типичный программист"));
    }
}
