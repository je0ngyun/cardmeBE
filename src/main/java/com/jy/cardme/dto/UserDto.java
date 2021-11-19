package com.jy.cardme.dto;

import com.jy.cardme.components.validation.DuplicateCheck;
import com.jy.cardme.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {
    @Data
    @AllArgsConstructor
    public static class SignInReq {
        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class SignInRes {
        private String userId;
        private String token;
    }

    @Data
    @AllArgsConstructor
    @DuplicateCheck(propertyNames={"userId"},message = "해당 아이디로 가입된 사용자가 존재합니다.")
    public static class SignUpReq {
        @Valid
        @NotBlank
        private String userId;
        @NotBlank
        private String userNm;
        @NotBlank
        private String userPw;
        @Email
        @NotBlank
        private String userEm;
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Info {
        private String userId;
        private String userNm;
        private String userEm;

        public static Info createFromEntity(final UserEntity user) {
            return builder()
                    .userId(user.getUserId())
                    .userNm(user.getUserNm())
                    .userEm(user.getUserEm())
                    .build();
        }
    }
}
