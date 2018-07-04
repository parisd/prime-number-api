package danparis.controllers;

import danparis.services.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@RestController
public class PrimeNumberController {

    @Autowired
    private PrimeNumberService primeNumberService;

    @RequestMapping("/primes/{maxNum}")
    public HttpEntity primes(@PathVariable final int maxNum) {
        final List<Integer> primes = primeNumberService.getPrimes(maxNum);
        final String responseBody = Arrays.toString(primes.toArray());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
