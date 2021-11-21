package com.jy.cardme.components.validation;

import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Optional;

public class ExistUserValidator implements ConstraintValidator<ExistCheck, Object> {

    private String message;
    private String[] propertyNames;

    private final UserRepository userRepository;

    @Autowired
    public ExistUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(ExistCheck constraintAnnotation) {
        message = constraintAnnotation.message();
        propertyNames = constraintAnnotation.propertyNames();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String userId = (String) getFieldValue(value,propertyNames[0]);
            Optional<UserEntity> optional = userRepository.findByUserId(userId);
            if (!optional.isPresent()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(propertyNames[0])
                        .addConstraintViolation();
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field dateField = clazz.getDeclaredField(fieldName);
        dateField.setAccessible(true);
        return dateField.get(object);
    }
}
