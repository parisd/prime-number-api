package danparis.integration.controllers;

import danparis.utils.TestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PrimeNumberControllerXmlIT extends PrimeNumberControllerBaseIT {

    @BeforeClass
    public static void setUp() throws Exception {
        mediaType = MediaType.APPLICATION_XML;
    }

    @Test
    public void getPrimesUpToMinus1() throws Exception {
        final int maxNum = -1;
        final HttpStatus expectedStatus = HttpStatus.OK;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_MINUS_ONE_XML;
        performTest(maxNum, expectedStatus, expectedResponseBody);
    }

    @Test
    public void getPrimesUpTo16() throws Exception {
        final int maxNum = 16;
        final HttpStatus expectedStatus = HttpStatus.OK;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_16_XML;
        performTest(maxNum, expectedStatus, expectedResponseBody);
    }

    @Test
    public void getPrimesUpTo128() throws Exception {
        final int maxNum = 128;
        final HttpStatus expectedStatus = HttpStatus.OK;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_128_XML;
        performTest(maxNum, expectedStatus, expectedResponseBody);
    }

    @Test
    public void getPrimesUpTo256() throws Exception {
        final int maxNum = 256;
        final HttpStatus expectedStatus = HttpStatus.OK;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_256_XML;
        performTest(maxNum, expectedStatus, expectedResponseBody);
    }

    @Test
    public void getPrimesUpTo10000() throws Exception {
        final int maxNum = 10000;
        final HttpStatus expectedStatus = HttpStatus.OK;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_10000_XML;
        performTest(maxNum, expectedStatus, expectedResponseBody);
    }

    // Fragile test as it depends on VM settings which aren't being forced.
    @Test
    public void getPrimesUpToIntegerMaxMinus1() throws Exception {
        final int maxNum = Integer.MAX_VALUE - 1;
        final HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        final String expectedResponseBody = "Requested array size exceeds VM limit";
        performTest(maxNum, expectedStatus, expectedResponseBody);
    }

    @Test
    public void getPrimesUpToIntegerMax() throws Exception {
        final int maxNum = Integer.MAX_VALUE;
        final HttpStatus expectedStatus = HttpStatus.OK;
        final String expectedResponseBody = TestData.PRIMES_UP_TO_INTEGER_MAX_XML;
        performTest(maxNum, expectedStatus, expectedResponseBody);
    }
}
