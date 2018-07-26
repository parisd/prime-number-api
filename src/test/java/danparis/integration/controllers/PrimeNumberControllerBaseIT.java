package danparis.integration.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class PrimeNumberControllerBaseIT {

    static MediaType mediaType;

    @LocalServerPort
    private int port;

    private String baseUri;

    @Autowired
    private TestRestTemplate template;

    static String EXPECTED_MINUS_ONE_RESPONSE_BODY;
    static String EXPECTED_16_RESPONSE_BODY;
    static String EXPECTED_128_RESPONSE_BODY;
    static String EXPECTED_256_RESPONSE_BODY;
    static String EXPECTED_10000_RESPONSE_BODY;
    static String EXPECTED_INTEGER_MAX_RESPONSE_BODY;
    static String EXPECTED_INTEGER_MAX_MINUS_ONE_RESPONSE_BODY;

    @Before
    public void before() {
        baseUri = "http://localhost:" + port + "/primes/";
    }

    @Test
    public void getPrimesUpToMinus1() throws Exception {
        final int maxNum = -1;
        final HttpStatus expectedStatus = HttpStatus.OK;
        performTest(maxNum, expectedStatus, EXPECTED_MINUS_ONE_RESPONSE_BODY);
    }

    @Test
    public void getPrimesUpTo16() throws Exception {
        final int maxNum = 16;
        final HttpStatus expectedStatus = HttpStatus.OK;
        performTest(maxNum, expectedStatus, EXPECTED_16_RESPONSE_BODY);
    }

    @Test
    public void getPrimesUpTo128() throws Exception {
        final int maxNum = 128;
        final HttpStatus expectedStatus = HttpStatus.OK;
        performTest(maxNum, expectedStatus, EXPECTED_128_RESPONSE_BODY);
    }

    @Test
    public void getPrimesUpTo256() throws Exception {
        final int maxNum = 256;
        final HttpStatus expectedStatus = HttpStatus.OK;
        performTest(maxNum, expectedStatus, EXPECTED_256_RESPONSE_BODY);
    }

    @Test
    public void getPrimesUpTo10000() throws Exception {
        final int maxNum = 10000;
        final HttpStatus expectedStatus = HttpStatus.OK;
        performTest(maxNum, expectedStatus, EXPECTED_10000_RESPONSE_BODY);
    }

    @Test
    public void getPrimesUpToIntegerMax() throws Exception {
        final int maxNum = Integer.MAX_VALUE;
        final HttpStatus expectedStatus = HttpStatus.OK;
        performTest(maxNum, expectedStatus, EXPECTED_INTEGER_MAX_RESPONSE_BODY);
    }

    // Fragile test as it depends on VM settings which aren't being forced.
    @Test
    public void getPrimesUpToIntegerMaxMinus1() throws Exception {
        final int maxNum = Integer.MAX_VALUE - 1;
        final HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        performTest(maxNum, expectedStatus, EXPECTED_INTEGER_MAX_MINUS_ONE_RESPONSE_BODY);
    }

    void performTest(final int maxNum, final HttpStatus expectedHttpStatus, final String expectedResponseBody) {
        final ResponseEntity<String> responseEntity = getMediaTypeResponse(maxNum);
        assertEquals(expectedHttpStatus, responseEntity.getStatusCode());
        assertEquals(expectedResponseBody, responseEntity.getBody());
    }

    /**
     * Accepts the max number to use for the request and will get the response entity using the test class MediaType.
     *
     * @param maxNum - max number to use for the request.
     *
     * @return - A ResponseEntity<String> with a body matching the requested MediaType.
     */
    private ResponseEntity<String> getMediaTypeResponse(final int maxNum) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", mediaType.toString());
        baseUri += maxNum;
        return template.exchange(baseUri, HttpMethod.GET, new HttpEntity<>(headers), String.class);
    }
}
