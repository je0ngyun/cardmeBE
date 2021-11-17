package com.jy.cardme.components.validation;

import com.jy.cardme.dao.CardRepository;
import com.jy.cardme.entity.CardEntity;
import com.jy.cardme.entity.UserEntity;
import com.jy.cardme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Optional;

public class DuplicateCheckValidator implements ConstraintValidator<DuplicateCheck, Object> {

    private String message;
    private String[] propertyNames;

    private final CardRepository cardRepository;

    @Autowired
    public DuplicateCheckValidator(CardRepository cardRepository, UserService userService) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void initialize(DuplicateCheck constraintAnnotation) {
        message = constraintAnnotation.message();
        propertyNames = constraintAnnotation.propertyNames();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String userId = (String) getFieldValue(value, propertyNames[0]);
            String cardName = (String) getFieldValue(value,propertyNames[1]);
            final UserEntity temp = UserEntity.builder(userId).id(new Long(-1)).build();
            final Optional<CardEntity> optional = cardRepository.findByUserAndCardName(temp, cardName);
            if (optional.isPresent()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(propertyNames[1])
                        .addConstraintViolation();
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();  //
        Field dateField = clazz.getDeclaredField(fieldName);
        dateField.setAccessible(true);
        return dateField.get(object);
    }
}
