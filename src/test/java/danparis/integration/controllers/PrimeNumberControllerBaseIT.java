package danparis.integration.controllers;

import org.junit.Before;
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

    @Before
    public void before() {
        baseUri = "http://localhost:" + port + "/primes/";
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
