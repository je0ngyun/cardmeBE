package com.jy.cardme.service;


import com.jy.cardme.commonException.UserNotFoundException;
import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.dto.UserInfoDto;
import com.jy.cardme.dto.UserSignInReq;
import com.jy.cardme.dto.UserSignInRes;
import com.jy.cardme.dto.UserSignUpReq;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    UserServiceImpl(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public UserInfoDto signUp(final UserSignUpReq userSignUpReq) {
        final UserEntity user = authService.pwEncrypting(
                UserEntity.builder(userSignUpReq.getUserId())
                        .userPw(userSignUpReq.getUserPw())
                        .userNm(userSignUpReq.getUserNm())
                        .userEm(userSignUpReq.getUserEm())
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build()
        );
        final UserEntity repoRet = userRepository.save(user);
        final UserInfoDto userInfo = UserInfoDto.create(repoRet);
        return userInfo;
    }

    @Override
    public UserSignInRes signIn(UserSignInReq userSignInReq) {
        final Optional<UserEntity> optional = userRepository.findById(userSignInReq.getUserId());
        if (!optional.isPresent()) {
            throw new UserNotFoundException(userSignInReq.getUserId());
        }
        final UserEntity user = optional.get();
        UserSignInRes userSignInRes = UserSignInRes.builder()
                .userId(user.getUserId())
                .token(authService.issuingToken(userSignInReq,user))
                .build();
        return userSignInRes;
    }

    @Override
    public UserInfoDto getUserInfo(final UserInfoDto userInfoDto) {
        final Optional<UserEntity> optional = userRepository.findById(userInfoDto.getUserId());
        if (!optional.isPresent()) {
            throw new UserNotFoundException(userInfoDto.getUserId());
        }
        final UserInfoDto userInfo = UserInfoDto.create(optional.get());
        return userInfo;
    }
}
