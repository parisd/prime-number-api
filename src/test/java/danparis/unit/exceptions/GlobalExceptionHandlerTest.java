package danparis.unit.exceptions;

import danparis.controllers.PrimeNumberController;
import danparis.exceptions.BadRequestException;
import danparis.exceptions.GlobalExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GlobalExceptionHandlerTest {

        private MockMvc mockMvc;

        @Mock
        PrimeNumberController primeNumberController;

        @Before
        public void setUp() {
            initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(primeNumberController).setControllerAdvice(
                    new GlobalExceptionHandler()).build();
        }

        @Test
        public void testGlobalExceptionHandlerError() throws Exception {
            when(primeNumberController.primesJson(500)).thenThrow(new BadRequestException(new Throwable("Bad Request")));

            mockMvc.perform(get("/primes/500")).andExpect(status().is(400)).andReturn();
        }

}
