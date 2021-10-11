package com.jy.cardme.advice;

import com.jy.cardme.commonException.RestException;
import com.jy.cardme.components.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<Object> handleUserNotExistException(RestException e) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ApiException apiException = ApiException.builder()
                .message(ResponseMessage.BAD_REQUEST)
                .httpStatus(httpStatus)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        final ApiException apiException = ApiException.builder()
                .message(errors)
                .httpStatus(httpStatus)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiException, httpStatus);
    }
}
