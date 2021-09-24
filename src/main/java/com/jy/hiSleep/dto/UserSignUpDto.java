package com.jy.hiSleep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignUpDto {
    private String userID;
    private String userNm;
    private String userPw;
    private String userEm;
}
