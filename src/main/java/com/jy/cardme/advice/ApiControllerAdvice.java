package com.jy.cardme.advice;

import com.jy.cardme.commonException.UserNotFoundException;
import com.jy.cardme.components.ResponseMessage;
import com.jy.cardme.components.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotExistException(UserNotFoundException e) {
        final ErrorRes errorRes = ErrorRes.builder()
                .message(ResponseMessage.USER_NOT_FOUND)
                .httpStatus(StatusCode.NOT_FOUND)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(errorRes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        final BindingResult bindingResult = ex.getBindingResult();
        final List<FieldError> errors = bindingResult.getFieldErrors();

        final List<FieldErrorDetail> details = errors.stream()
                .map(error -> FieldErrorDetail.builder()
                        .field(error.getField())
                        .value(error.getRejectedValue().toString())
                        .reason(error.getDefaultMessage()).build())
                .collect(Collectors.toList());

        final ErrorRes errorRes = ErrorRes.builder()
                .message(ResponseMessage.ARGUMENT_NOT_VALID)
                .httpStatus(StatusCode.BAD_REQUEST)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .errors(details)
                .build();

        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }
}
