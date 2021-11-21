package com.jy.cardme.components.validation;

import com.jy.cardme.components.commons.ResponseMessage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistUserValidator.class})
public @interface ExistCheck {
    String message() default ResponseMessage.NOT_FOUND;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] propertyNames();
}
