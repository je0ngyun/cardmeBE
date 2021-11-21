package com.jy.cardme.dto;

import com.jy.cardme.components.card.Card;
import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.components.validation.DuplicateCheck;
import com.jy.cardme.components.validation.Enum;
import com.jy.cardme.components.validation.ExistCheck;
import com.jy.cardme.entity.CardEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

public class CardDto {
    @Data
    @AllArgsConstructor
    @DuplicateCheck(propertyNames={"userId","cardName"},message = ResponseMessage.DUPLICATE_CARD_NAME)
    @ExistCheck(propertyNames = {"userId"},message = ResponseMessage.NOT_FOUND_USER)
    public static class SignReq {
        @NotBlank
        @Valid
        private String userId;
        @NotBlank
        @Enum(enumClass = Card.CardType.class,
                ignoreCase = true,
                message = ResponseMessage.NOT_FOUND_CARD_TYPE)
        private String cardType;
        @NotBlank
        @Valid
        private String cardName;
        @NotBlank
        private String cardTitle;
        @NotBlank
        private String cardMotto;
        @Email
        @NotBlank
        private String cardEmail;
        @NotBlank
        private String cardDepartment;
        @NotNull
        private List<String> cardSkills;
        @Pattern(regexp = "^#(([0-9a-fA-F]{2}){3}|([0-9a-fA-F]){3})$",
                message = ResponseMessage.NOT_VALID_COLOR_CODE)
        @NotBlank
        private String cardHighlightColor;

        public String cardSkillsToStr() {
            return cardSkills
                    .stream()
                    .map(s -> String.valueOf(s))
                    .collect(Collectors.joining(","));
        }
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Info {
        private String cardName;
        private String cardTitle;
        private String cardMotto;
        private String cardEmail;
        private String cardDepartment;
        private List<String> cardSkills;
        private String cardType;
        private String cardHighlightColor;

        public static Info createFromEntity(final CardEntity card) {
            return builder()
                    .cardName(card.getCardName())
                    .cardTitle(card.getCardTitle())
                    .cardMotto(card.getCardMotto())
                    .cardEmail(card.getCardEmail())
                    .cardDepartment(card.getCardDepartment())
                    .cardSkills(card.cardSkillsToList())
                    .cardType(card.getCardType().toString())
                    .cardHighlightColor(card.getCardHighlightColor())
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    public static class UseReq {
        @NotBlank
        private String userId;
        @NotBlank
        private String cardName;
    }
}
