package com.jy.cardme.components;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DefaultRes<T> {
    private final String message;
    private final int httpStatus;
    private final ZonedDateTime timestamp;
    private final T data;
}
