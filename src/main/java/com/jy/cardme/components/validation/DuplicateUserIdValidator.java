package com.jy.cardme.components.validation;

import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.dto.UserDto;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class DuplicateUserIdValidator implements ConstraintValidator<DuplicateCheck, UserDto.SignUpReq> {

    private String message;
    private String[] propertyNames;

    private final UserRepository userRepository;

    @Autowired
    public DuplicateUserIdValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(DuplicateCheck constraintAnnotation) {
        message = constraintAnnotation.message();
        propertyNames = constraintAnnotation.propertyNames();
    }

    @Override
    public boolean isValid(UserDto.SignUpReq value, ConstraintValidatorContext context) {
        try {
            String userId = value.getUserId();
            Optional<UserEntity> optional = userRepository.findByUserId(userId);
            if (optional.isPresent()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(propertyNames[0])
                        .addConstraintViolation();
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
