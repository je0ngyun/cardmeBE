package com.jy.cardme.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class DefaultRes<T> {
    private String message;
    private final HttpStatus httpStatus;
    private T data;
}
