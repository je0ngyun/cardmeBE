package com.jy.cardme.service;


import com.jy.cardme.commonException.UserNotFoundException;
import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.dto.*;
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
    public UserDto.Info signUp(final UserDto.SignUpReq userSignUpReq) {
        final UserEntity user = authService.pwEncrypting(
                UserEntity.builder(userSignUpReq.getUserId())
                        .userPw(userSignUpReq.getUserPw())
                        .userNm(userSignUpReq.getUserNm())
                        .userEm(userSignUpReq.getUserEm())
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build()
        );
        final UserEntity repoRet = userRepository.save(user);
        final UserDto.Info userInfo = UserDto.Info.createFromEntity(repoRet);
        return userInfo;
    }

    @Override
    public UserDto.SignInRes signIn(UserDto.SignInReq userSignInReq) {
        final Optional<UserEntity> optional = userRepository.findById(userSignInReq.getUserId());
        if (!optional.isPresent()) {
            throw new UserNotFoundException(userSignInReq.getUserId());
        }
        final UserEntity user = optional.get();
        UserDto.SignInRes userSignInRes = UserDto.SignInRes.builder()
                .userId(user.getUserId())
                .token(authService.issuingToken(userSignInReq,user))
                .build();
        return userSignInRes;
    }

    @Override
    public UserDto.Info getUserInfo(final UserDto.Info userInfo) {
        final Optional<UserEntity> optional = userRepository.findById(userInfo.getUserId());
        if (!optional.isPresent()) {
            throw new UserNotFoundException(userInfo.getUserId());
        }
        final UserDto.Info info = UserDto.Info.createFromEntity(optional.get());
        return info;
    }
}
