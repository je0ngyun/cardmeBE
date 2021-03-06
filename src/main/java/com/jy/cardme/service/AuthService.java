package com.jy.cardme.service;

import com.jy.cardme.dto.UserDto;
import com.jy.cardme.entity.UserEntity;

public interface AuthService {
    /* 공통 서비스
     * 비밀번호 암호화
     * 비밀번호 확인
     * 토큰 발급
     */
    UserEntity encryptingPw(UserEntity user);

    String issuingToken(UserDto.SignInReq userSignInReq, UserEntity user);

    boolean checkingPw(String pw, UserEntity user);
}
