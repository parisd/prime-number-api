package danparis.unit.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import danparis.utils.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeNumberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPrimesUpto16() throws Exception {
        performTest(16, TestData.PRIMES_UP_TO_16);
    }

    @Test
    public void getPrimesUpTo128() throws Exception {
        performTest(128, TestData.PRIMES_UP_TO_128);
    }

    @Test
    public void getPrimesUpTo256() throws Exception {
        performTest(256, TestData.PRIMES_UP_TO_256);
    }

    @Test
    public void getPrimesUpTo10000() throws Exception {
        performTest(10000, TestData.PRIMES_UP_TO_10000);
    }

    private void performTest(final Integer maxNum, final String expectedResponseBody) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/" + maxNum).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedResponseBody)));
    }
}
