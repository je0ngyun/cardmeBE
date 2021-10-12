package com.jy.cardme.advice;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FieldErrorDetail {
    private String field;
    private String value;
    private String reason;
}
