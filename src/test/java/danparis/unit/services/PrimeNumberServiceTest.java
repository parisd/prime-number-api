package danparis.unit.services;

import danparis.exceptions.BadRequestException;
import danparis.services.PrimeNumberService;
import danparis.utils.TestData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PrimeNumberServiceTest {
    @TestConfiguration
    static class PrimeNumberServiceTestContextConfiguration {
    @Bean
    public PrimeNumberService primeNumberService() {
            return new PrimeNumberService();
        }
    }

    @Autowired
    private PrimeNumberService primeNumberService;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getPrimesUptoMinus1() throws Exception {
        final List<Integer> primes = primeNumberService.getPrimes(-1);
        assertEquals(TestData.PRIMES_UP_TO_MINUS_ONE_LIST, primes);
    }

    @Test
    public void getPrimesUpto16() throws Exception {
        final List<Integer> primes = primeNumberService.getPrimes(16);
        assertEquals(TestData.PRIMES_UP_TO_16_LIST, primes);
    }

    @Test
    public void getPrimesUpto128() throws Exception {
        final List<Integer> primes = primeNumberService.getPrimes(128);
        assertEquals(TestData.PRIMES_UP_TO_128_LIST, primes);
    }

    @Test
    public void getPrimesUpto256() throws Exception {
        final List<Integer> primes = primeNumberService.getPrimes(256);
        assertEquals(TestData.PRIMES_UP_TO_256_LIST, primes);
    }

    @Test
    public void getPrimesUpto10000() throws Exception {
        final List<Integer> primes = primeNumberService.getPrimes(10000);
        assertEquals(TestData.PRIMES_UP_TO_10000_LIST, primes);
    }

    // Fragile test as it depends on VM settings which aren't being forced.
    @Test
    public void getPrimesUptoIntegerMaxMinus1() throws Exception {
        expectedException.expect(BadRequestException.class);
        expectedException.expectMessage("Requested array size exceeds VM limit");
        final List<Integer> primes = primeNumberService.getPrimes(Integer.MAX_VALUE - 1);
    }

    @Test
    public void getPrimesUptoIntegerMax() throws Exception {
        final List<Integer> primes = primeNumberService.getPrimes(Integer.MAX_VALUE);
        assertEquals(new ArrayList<>(), primes);
    }
}
