package com.jy.cardme.advice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ErrorRes {
    private final String message;
    private final int httpStatus;
    private final ZonedDateTime timestamp;
    private List<FieldErrorDetail> errors;
}
