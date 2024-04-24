package br.com.neurotech.challenge.advice;

import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.exception.ErrorResponse;
import br.com.neurotech.challenge.exception.InvalidClientIDException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Calendar;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(InvalidClientIDException.class)
    public ResponseEntity<ErrorResponse> iDInvalidException(HttpServletRequest request, Exception ex) {

        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .timestamp(Calendar.getInstance())
                .status(400)
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> clientNotFoundException(HttpServletRequest request, Exception ex) {

        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .timestamp(Calendar.getInstance())
                .status(404)
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), HttpStatus.NOT_FOUND);
    }
}
