package com.jy.cardme.service;


import com.jy.cardme.commonException.UserNotFoundException;
import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.dto.UserInfoDto;
import com.jy.cardme.dto.UserSignUpDto;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserInfoDto signUp(final UserSignUpDto userSignUpDto) {
        final UserEntity user = authService.pwEncryption(
                UserEntity.builder(userSignUpDto.getUserId())
                .userPw(userSignUpDto.getUserPw())
                .userNm(userSignUpDto.getUserNm())
                .userEm(userSignUpDto.getUserEm())
                .build()
        );
        final UserEntity repoRet = userRepository.save(user);
        final UserInfoDto userInfo = UserInfoDto.create(repoRet);
        return userInfo;
    }

    @Override
    public UserInfoDto getUserInfo(final UserInfoDto userInfoDto) {
        final Optional<UserEntity> optional = userRepository.findById(userInfoDto.getUserId());
        if(!optional.isPresent()) {
            throw new UserNotFoundException(userInfoDto.getUserId());
        }
        final UserInfoDto userInfo = UserInfoDto.create(optional.get());
        return userInfo;
    }
}
