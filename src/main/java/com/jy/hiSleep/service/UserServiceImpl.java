package com.jy.hiSleep.service;


import com.jy.hiSleep.dao.UserRepository;
import com.jy.hiSleep.dto.UserInfoDto;
import com.jy.hiSleep.dto.UserSignUpDto;
import com.jy.hiSleep.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserInfoDto signUp(final UserSignUpDto signUpDto) {
        final UserEntity user = UserEntity.builder(signUpDto.getUserId())
                .userPw(signUpDto.getUserPw())
                .userNm(signUpDto.getUserNm())
                .userEm(signUpDto.getUserEm())
                .build();
        final UserEntity repoRet = userRepository.save(user);
        final UserInfoDto userInfoDto = UserInfoDto.create(repoRet);
        return userInfoDto;
    }
}
