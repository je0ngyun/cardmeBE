package com.jy.cardme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserSignInReq {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPw;
}
