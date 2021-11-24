package com.jy.cardme.dto;

import com.jy.cardme.components.commons.ResponseMessage;
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
    @DuplicateCheck(propertyNames={"userId"},message = ResponseMessage.DUPLICATE_USER_ID)
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
    @AllArgsConstructor
    public static class UpdateReq{
        @NotBlank
        private String userId;
        @NotBlank
        private String userNm;
        @Email
        @NotBlank
        private String userEm;
    }

    @Data
    @AllArgsConstructor
    public static class WithdrawalReq {
        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
    }

    @Data
    @AllArgsConstructor
    public static class ChangePwReq{
        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
        @NotBlank
        private String userNewPw;
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
