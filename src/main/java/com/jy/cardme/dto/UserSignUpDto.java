package com.jy.cardme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
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
}
