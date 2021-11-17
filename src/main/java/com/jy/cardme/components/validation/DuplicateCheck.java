package com.jy.cardme.components.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateCheckValidator.class)
public @interface DuplicateCheck {
    String message() default "중복된 값이 존재합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] propertyNames();
}
