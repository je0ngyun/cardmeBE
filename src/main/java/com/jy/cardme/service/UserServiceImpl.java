package com.jy.cardme.service;


import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.dto.UserInfoDto;
import com.jy.cardme.dto.UserSignUpDto;
import com.jy.cardme.entity.UserEntity;
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
