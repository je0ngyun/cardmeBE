package com.jy.cardme.service;

import com.jy.cardme.dto.*;
import com.jy.cardme.entity.UserEntity;

public interface UserService {
    /* 공통 서비스
     * 회원 가입
     * 로그인
     * 회원정보 수정
     * 회원정보 조회
     * 회원 탈퇴
     * 아이디 찾기
     * 비밀번호 변경
     * Entity 리턴 (JPA 관계 참조용)
     */
    UserDto.Info signUp(UserDto.SignUpReq userSignUpReq);

    UserDto.SignInRes signIn(UserDto.SignInReq userSignInReq);

    UserDto.Info getUserInfo(UserDto.Info userInfo);

    UserEntity getUserEntity(String userId);

    UserDto.Info withdrawal(UserDto.WithdrawalReq userWithdrawalReq);

    UserDto.Info updateUser(UserDto.UpdateReq userUpdateReq);

    UserDto.Info changePw(UserDto.ChangePwReq userChangePwReq);
}
