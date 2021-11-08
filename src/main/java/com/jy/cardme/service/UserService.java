package com.jy.cardme.service;

import com.jy.cardme.dto.*;
import com.jy.cardme.entity.UserEntity;

public interface UserService {
    /* 공통 서비스
     * 회원정보 조회
     * 아이디 찾기
     * 비밀번호 변경
     * FK를 가진 객체의 참조용 Entity 리턴 함수
     */
    UserDto.Info signUp(UserDto.SignUpReq userSignUpReq);

    UserDto.SignInRes signIn(UserDto.SignInReq userSignInReq);

    UserDto.Info getUserInfo(UserDto.Info userInfo);

    UserEntity getUserEntity(String userId);
}
