package com.jy.cardme.components.validation;

import com.jy.cardme.dao.CardRepository;
import com.jy.cardme.dto.CardDto;
import com.jy.cardme.entity.CardEntity;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class DuplicateCardNameValidator implements ConstraintValidator<DuplicateCheck, CardDto.SignReq> {

    private String message;
    private String[] propertyNames;

    private final CardRepository cardRepository;

    @Autowired
    public DuplicateCardNameValidator(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void initialize(DuplicateCheck constraintAnnotation) {
        message = constraintAnnotation.message();
        propertyNames = constraintAnnotation.propertyNames();
    }

    @Override
    public boolean isValid(CardDto.SignReq value, ConstraintValidatorContext context) {
        try {
            String userId = value.getUserId();
            String cardName = value.getCardName();
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
}
