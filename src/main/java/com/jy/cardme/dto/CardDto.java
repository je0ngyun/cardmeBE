package com.jy.cardme.dto;

import com.jy.cardme.entity.CardEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CardDto {
    @Data
    @AllArgsConstructor
    public static class SignReq {
        @NotBlank
        private String userId;
        @NotBlank
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
        @NotBlank
        private String cardSkill;
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class SignRes {
        private String cardName;
        private String cardTitle;
        private String cardMotto;
        private String cardEmail;
        private String cardDepartment;
        private String cardSkill;

        public static SignRes createFromEntity(final CardEntity card) {
            return builder()
                    .cardName(card.getCardName())
                    .cardTitle(card.getCardTitle())
                    .cardMotto(card.getCardMotto())
                    .cardEmail(card.getCardEmail())
                    .cardDepartment(card.getCardDepartment())
                    .cardSkill(card.getCardSkill())
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
