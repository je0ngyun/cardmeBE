package com.jy.cardme.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Builder
@AllArgsConstructor
@Getter
public class ApiException {
    private final Object message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
