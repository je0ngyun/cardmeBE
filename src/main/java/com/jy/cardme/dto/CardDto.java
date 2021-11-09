package com.jy.cardme.dto;

import com.jy.cardme.entity.CardEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

public class CardDto {
    @Data
    @AllArgsConstructor
    public static class SignReq {
        @NotBlank
        private String userId;
        @NotBlank
        private String cardType;
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
        @NotNull
        private List<String> cardSkills;
        @Pattern(regexp = "^#(([0-9a-fA-F]{2}){3}|([0-9a-fA-F]){3})$",
                message = "3자리 또는 6자리 16진수 형식의 컬러코드만 유효합니다.")
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
