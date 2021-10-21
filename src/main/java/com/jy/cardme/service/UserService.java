package com.jy.cardme.service;

import com.jy.cardme.dto.*;

public interface UserService {
    /* 공통 서비스
     * 회원정보 조회
     * 아이디 찾기
     * 비밀번호 변경
     */
    UserDto.Info signUp(UserDto.SignUpReq userSignUpReq);
    UserDto.SignInRes signIn(UserDto.SignInReq userSignInReq);
    UserDto.Info getUserInfo(UserDto.Info userInfo);
}
