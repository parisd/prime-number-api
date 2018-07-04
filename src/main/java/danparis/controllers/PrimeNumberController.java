package danparis.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PrimeNumberController {
    
    @RequestMapping("/primes/{maxNum}")
    public HttpEntity primes(@PathVariable final int maxNum) {
        return new ResponseEntity(String.format("%d", maxNum), HttpStatus.OK);
    }
}
