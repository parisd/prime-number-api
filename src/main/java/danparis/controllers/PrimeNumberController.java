package danparis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import danparis.model.PrimesResponse;
import danparis.services.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

@RestController
public class PrimeNumberController {

    // TODO: Consider adding optional @RequestParam fields to do algorithm toggling.

    @Autowired
    private PrimeNumberService primeNumberService;

    @RequestMapping(value = "/primes/{maxNum}", produces = {"application/json"})
    public HttpEntity primesJson(@PathVariable final int maxNum) {
        final List<Integer> primes = primeNumberService.getPrimes(maxNum);
        final PrimesResponse primesResponse = new PrimesResponse(maxNum, primes);

        final ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = "";
        try {
            jsonResponse = mapper.writeValueAsString(primesResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/primes/{maxNum}", produces = {"application/xml"})
    public HttpEntity primesXml(@PathVariable final int maxNum) {
        final List<Integer> primes = primeNumberService.getPrimes(maxNum);
        final PrimesResponse primesResponse = new PrimesResponse(maxNum, primes);
        String xmlString = "";
        try {
            final JAXBContext context = JAXBContext.newInstance(PrimesResponse.class);
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            final StringWriter sw = new StringWriter();
            marshaller.marshal(primesResponse, sw);
            xmlString = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(xmlString, HttpStatus.OK);
    }

    // TODO: Implement a function to do some validation (and any other required actions) on the input.
    /*
    private void doStuffWithInput() {

    }
    */
}
