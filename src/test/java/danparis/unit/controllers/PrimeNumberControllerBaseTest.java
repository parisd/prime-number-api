package danparis.unit.controllers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class PrimeNumberControllerBaseTest {

    static MediaType mediaType;

    @Autowired
    private MockMvc mvc;

    void performTest(final Integer maxNum, final ResultMatcher expectedStatus, final String expectedResponseBody)
            throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/" + maxNum).accept(mediaType))
                .andExpect(expectedStatus)
                .andExpect(content().string(equalTo(expectedResponseBody)));
    }
}
