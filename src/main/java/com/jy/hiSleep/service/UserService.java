package com.jy.hiSleep.service;

import com.jy.hiSleep.dto.UserInfoDto;
import com.jy.hiSleep.dto.UserSignUpDto;

public interface UserService {
    /* 공통 서비스
     * 회원등록
     * 회원정보 조회
     * 아이디 찾기
     * 비밀번호 변경
     */
    UserInfoDto signUp(UserSignUpDto userSignUpDto);
}
