package com.jy.cardme.service;


import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.dto.UserDto;
import com.jy.cardme.entity.UserEntity;
import com.jy.cardme.exception.Common404Exception;
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
        final UserEntity user = authService.encryptingPw(
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
    public UserDto.SignInRes signIn(final UserDto.SignInReq userSignInReq) {
        final Optional<UserEntity> optional = userRepository.findByUserId(userSignInReq.getUserId());
        if (!optional.isPresent()) {
            throw new Common404Exception(ResponseMessage.NOT_FOUND_USER);
        }
        final UserEntity user = optional.get();
        UserDto.SignInRes userSignInRes = UserDto.SignInRes.builder()
                .userId(user.getUserId())
                .token(authService.issuingToken(userSignInReq, user))
                .build();
        return userSignInRes;
    }

    @Override
    public UserDto.Info getUserInfo(final UserDto.Info userInfo) {
        final Optional<UserEntity> optional = userRepository.findByUserId(userInfo.getUserId());
        if (!optional.isPresent()) {
            throw new Common404Exception(ResponseMessage.NOT_FOUND_USER);
        }
        final UserDto.Info info = UserDto.Info.createFromEntity(optional.get());
        return info;
    }

    @Override
    public UserEntity getUserEntity(final String userId) {
        final Optional<UserEntity> optional = userRepository.findByUserId(userId);
        if (!optional.isPresent()) {
            throw new Common404Exception(ResponseMessage.NOT_FOUND_USER);
        }
        return optional.get();
    }

    @Override
    public UserDto.Info withdrawal(UserDto.WithdrawalReq userWithdrawalReq) {
        final Optional<UserEntity> optional = userRepository.findByUserId(userWithdrawalReq.getUserId());
        if (!optional.isPresent()) {
            throw new Common404Exception(ResponseMessage.NOT_FOUND_USER);
        }
        final UserEntity user = optional.get();
        final UserDto.Info userInfo = UserDto.Info.createFromEntity(user);
        if (authService.checkingPw(userWithdrawalReq.getUserPw(), user)) {
            userRepository.delete(user);
        }
        return userInfo;
    }

    @Override
    public UserDto.Info updateUser(UserDto.UpdateReq userUpdateReq) {
        final Optional<UserEntity> optional = userRepository.findByUserId(userUpdateReq.getUserId());
        UserEntity user = optional.get();
        user.setUserEm(userUpdateReq.getUserEm());
        user.setUserNm(userUpdateReq.getUserNm());
        final UserEntity repoRet = userRepository.saveAndFlush(user);
        final UserDto.Info userInfo = UserDto.Info.createFromEntity(repoRet);
        return userInfo;
    }

    @Override
    public UserDto.Info changePw(UserDto.ChangePwReq userChangePwReq) {
        final Optional<UserEntity> optional = userRepository.findByUserId(userChangePwReq.getUserId());
        if (!optional.isPresent()) {
            throw new Common404Exception(ResponseMessage.NOT_FOUND_USER);
        }
        UserEntity user = optional.get();
        if (authService.checkingPw(userChangePwReq.getUserPw(), user)) {
            user.setUserPw(userChangePwReq.getUserNewPw());
            authService.encryptingPw(user);
            userRepository.saveAndFlush(user);
        }
        final UserDto.Info userInfo = UserDto.Info.createFromEntity(user);
        return userInfo;
    }
}
