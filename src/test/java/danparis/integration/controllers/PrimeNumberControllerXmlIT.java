package danparis.integration.controllers;

import danparis.utils.TestData;
import org.junit.BeforeClass;
import org.springframework.http.MediaType;

public class PrimeNumberControllerXmlIT extends PrimeNumberControllerBaseIT {

    @BeforeClass
    public static void setUp() throws Exception {
        mediaType = MediaType.APPLICATION_XML;
        EXPECTED_MINUS_ONE_RESPONSE_BODY = TestData.PRIMES_UP_TO_MINUS_ONE_XML;
        EXPECTED_16_RESPONSE_BODY = TestData.PRIMES_UP_TO_16_XML;
        EXPECTED_128_RESPONSE_BODY = TestData.PRIMES_UP_TO_128_XML;
        EXPECTED_256_RESPONSE_BODY = TestData.PRIMES_UP_TO_256_XML;
        EXPECTED_10000_RESPONSE_BODY = TestData.PRIMES_UP_TO_10000_XML;
        EXPECTED_INTEGER_MAX_RESPONSE_BODY = TestData.PRIMES_UP_TO_INTEGER_MAX_XML;
        EXPECTED_INTEGER_MAX_MINUS_ONE_RESPONSE_BODY = "Requested array size exceeds VM limit";
    }
}
