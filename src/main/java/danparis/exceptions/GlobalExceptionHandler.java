package danparis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO: Improve exception handling.

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<String> handleBadRequest(RuntimeException rte) {
        return new ResponseEntity<>(rte.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
