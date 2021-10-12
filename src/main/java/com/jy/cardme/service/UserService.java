package com.jy.cardme.service;

import com.jy.cardme.dto.UserInfoDto;
import com.jy.cardme.dto.UserSignUpDto;

public interface UserService {
    /* 공통 서비스
     * 회원정보 조회
     * 아이디 찾기
     * 비밀번호 변경
     */
    UserInfoDto signUp(UserSignUpDto userSignUpDto);
    UserInfoDto getUserInfo(UserInfoDto userInfoDto);
}
