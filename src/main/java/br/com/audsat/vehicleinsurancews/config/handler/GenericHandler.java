package br.com.audsat.vehicleinsurancews.config.handler;

import br.com.audsat.vehicleinsurancews.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handlerBannerNotFoundException(NotFoundException e) {
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        e.printStackTrace();
        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity<String> handlerBannerBadCredentialsException(BadCredentialsException e) {
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        e.printStackTrace();
        return response;
    }
}
