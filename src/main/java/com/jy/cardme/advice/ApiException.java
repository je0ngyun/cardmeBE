package com.jy.cardme.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@Builder
public class ApiException {
    private final Object message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
