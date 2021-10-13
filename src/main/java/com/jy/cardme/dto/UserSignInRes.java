package com.jy.cardme.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserSignInRes {
    private String userId;
    private String token;
}
