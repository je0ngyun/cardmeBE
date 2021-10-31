package com.jy.cardme.advice;

import com.jy.cardme.controller.ErrorRes;
import com.jy.cardme.exception.*;
import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.components.commons.StatusCode;
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

    @ExceptionHandler(value = {CommonTokenException.class})
    public ResponseEntity handleJwtTokenException(CommonTokenException ex) {
        final ErrorRes errorRes = ErrorRes.builder()
                .message(ex.getMessage())
                .httpStatus(StatusCode.UNAUTHORIZED)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity(errorRes, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {NotAuthorizationException.class})
    public ResponseEntity handleNotAuthorizationException(NotAuthorizationException ex) {
        final ErrorRes errorRes = ErrorRes.builder()
                .message(ex.getMessage())
                .httpStatus(StatusCode.FORBIDDEN)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity(errorRes, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {Common400Exception.class})
    public ResponseEntity handleCommon400Exception(Common400Exception ex) {
        final ErrorRes errorRes = ErrorRes.builder()
                .message(ex.getMessage())
                .httpStatus(StatusCode.BAD_REQUEST)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity(errorRes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Common404Exception.class})
    public ResponseEntity handleCommon404Exception(Common404Exception ex) {
        final ErrorRes errorRes = ErrorRes.builder()
                .message(ex.getMessage())
                .httpStatus(StatusCode.NOT_FOUND)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity(errorRes, HttpStatus.NOT_FOUND);
    }
}
