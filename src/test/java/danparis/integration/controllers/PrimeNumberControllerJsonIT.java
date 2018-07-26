package danparis.integration.controllers;

import danparis.utils.TestData;
import org.junit.BeforeClass;
import org.springframework.http.*;

public class PrimeNumberControllerJsonIT extends PrimeNumberControllerBaseIT {

    @BeforeClass
    public static void setUp() throws Exception {
        mediaType = MediaType.APPLICATION_JSON;
        EXPECTED_MINUS_ONE_RESPONSE_BODY = TestData.PRIMES_UP_TO_MINUS_ONE_JSON;
        EXPECTED_16_RESPONSE_BODY = TestData.PRIMES_UP_TO_16_JSON;
        EXPECTED_128_RESPONSE_BODY = TestData.PRIMES_UP_TO_128_JSON;
        EXPECTED_256_RESPONSE_BODY = TestData.PRIMES_UP_TO_256_JSON;
        EXPECTED_10000_RESPONSE_BODY = TestData.PRIMES_UP_TO_10000_JSON;
        EXPECTED_INTEGER_MAX_RESPONSE_BODY = TestData.PRIMES_UP_TO_INTEGER_MAX_JSON;
        EXPECTED_INTEGER_MAX_MINUS_ONE_RESPONSE_BODY = "Requested array size exceeds VM limit";
    }
}
