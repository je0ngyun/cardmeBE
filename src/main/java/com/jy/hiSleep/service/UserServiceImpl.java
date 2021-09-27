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
    public UserInfoDto signUp(final UserSignUpDto userSignUpDto) {
        final UserEntity user = UserEntity.builder(userSignUpDto.getUserId())
                .userPw(userSignUpDto.getUserPw())
                .userNm(userSignUpDto.getUserNm())
                .userEm(userSignUpDto.getUserEm())
                .build();
        final UserEntity repoRet = userRepository.save(user);
        UserInfoDto userInfo = UserInfoDto.create(repoRet);
        return userInfo;
    }

    @Override
    public UserInfoDto getUserInfo(final UserInfoDto userInfoDto) {
        final UserEntity repoRet = userRepository.getById(userInfoDto.getUserId());
        UserInfoDto userInfo = UserInfoDto.create(repoRet);
        return userInfo;
    }
}
