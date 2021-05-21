package br.com.conexa.hospital.api.handler;

import br.com.conexa.hospital.api.exceptions.ObjectNotFoundException;
import br.com.conexa.hospital.api.exceptions.StandardError;
import br.com.conexa.hospital.api.exceptions.ValidationStandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class RestHandler {

    private static final long MILLI_NOW = System.currentTimeMillis();
    private static final String IVALID_ARG = "Invalid argument";
    private static final String WRONG_VERB = "Wrong Http verb to this request: ";

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(ResponseStatusException e, HttpServletRequest request) {
        String message = e.getReason();
        HttpStatus httpStatus = e.getStatus();
        StandardError error = StandardError.builder()
                .timestamp(MILLI_NOW)
                .statusCode(httpStatus.value())
                .httpStatus(httpStatus.name())
                .msg(message)
                .build();
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        String message = e.getMessage();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError error = StandardError.builder()
                .timestamp(MILLI_NOW)
                .statusCode(httpStatus.value())
                .httpStatus(httpStatus.name())
                .msg(message)
                .build();
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleMethodNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
        String message = e.getMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError error = StandardError.builder()
                .timestamp(MILLI_NOW)
                .statusCode(httpStatus.value())
                .httpStatus(httpStatus.name())
                .msg(message)
                .build();
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationStandardError> handleValidationError(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<String> messages = result.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(toList());

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ValidationStandardError error = new ValidationStandardError(System.currentTimeMillis(), httpStatus.value(), httpStatus.name(), messages);
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleValidationError(IllegalArgumentException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError error = StandardError.builder()
                .timestamp(MILLI_NOW)
                .statusCode(httpStatus.value())
                .httpStatus(httpStatus.name())
                .msg(IVALID_ARG)
                .build();
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> handleMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        StandardError error = StandardError.builder()
                .timestamp(MILLI_NOW)
                .statusCode(httpStatus.value())
                .httpStatus(httpStatus.name())
                .msg(WRONG_VERB + e.getMessage())
                .build();
        return ResponseEntity.status(httpStatus).body(error);
    }
}
