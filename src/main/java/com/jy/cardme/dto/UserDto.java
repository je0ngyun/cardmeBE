package com.jy.cardme.dto;

import com.jy.cardme.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {
    @Data
    @AllArgsConstructor
    public static class SignInReq{
        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class SignInRes{
        private String userId;
        private String token;
    }

    @Data
    @AllArgsConstructor
    public static class SignUpReq{
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
    public static class Info{
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
