package danparis.integration.controllers;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import danparis.utils.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeNumberControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/primes/");
    }

    @Test
    public void getPrimesUpTo16() throws Exception {
        final Integer maxNum = 16;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_16;
        performTest(maxNum, expectedResponseBody);
    }

    @Test
    public void getPrimesUpTo128() throws Exception {
        final Integer maxNum = 128;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_128;
        performTest(maxNum, expectedResponseBody);
    }

    @Test
    public void getPrimesUpTo256() throws Exception {
        final Integer maxNum = 256;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_256;
        performTest(maxNum, expectedResponseBody);
    }

    @Test
    public void getPrimesUpTo10000() throws Exception {
        final Integer maxNum = 10000;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_10000;
        performTest(maxNum, expectedResponseBody);
    }

    private void performTest(final int maxNum, final String expectedResponseBody) {
        ResponseEntity<String> response = template.getForEntity(base.toString() + maxNum,
                String.class);
        assertThat(response.getBody(), equalTo(expectedResponseBody));
    }
}
