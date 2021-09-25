package com.jy.hiSleep.dto;

import com.jy.hiSleep.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class UserSignUpDto {


    @NotBlank
    private String userId;
    @NotBlank
    private String userNm;
    @NotBlank
    private String userPw;
    @Email
    @NotBlank
    private String userEm;

    public static UserSignUpDto create(final UserEntity user) {
        return builder()
                .userId(user.getUserId())
                .userNm(user.getUserNm())
                .userPw(user.getUserPw())
                .userEm(user.getUserEm())
                .build();
    }
}
