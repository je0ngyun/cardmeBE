package com.jy.cardme.service;

import com.jy.cardme.dto.UserDto;
import com.jy.cardme.entity.UserEntity;

public interface AuthService {
    /* 공통 서비스
     * 비밀번호 암호화
     * 토큰 발급
     * 토큰 인증
     */
    UserEntity pwEncrypting(UserEntity user);
    String issuingToken(UserDto.SignInReq userSignInReq, UserEntity user);
}
