package com.jy.cardme.service;

import com.jy.cardme.dto.UserInfoDto;
import com.jy.cardme.dto.UserSignInReq;
import com.jy.cardme.dto.UserSignInRes;
import com.jy.cardme.dto.UserSignUpReq;

public interface UserService {
    /* 공통 서비스
     * 회원정보 조회
     * 아이디 찾기
     * 비밀번호 변경
     */
    UserInfoDto signUp(UserSignUpReq userSignUpReq);
    UserSignInRes signIn(UserSignInReq userSignInReq);
    UserInfoDto getUserInfo(UserInfoDto userInfoDto);
}
