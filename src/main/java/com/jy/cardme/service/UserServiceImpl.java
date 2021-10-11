package com.jy.cardme.service;


import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.dto.UserInfoDto;
import com.jy.cardme.dto.UserSignUpDto;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserInfoDto signUp(final UserSignUpDto userSignUpDto) {
        final String rawPassword = userSignUpDto.getUserPw();
        final String encodedPassword = passwordEncoder.encode(rawPassword);
        final UserEntity user = UserEntity.builder(userSignUpDto.getUserId())
                .userPw(encodedPassword)
                .userNm(userSignUpDto.getUserNm())
                .userEm(userSignUpDto.getUserEm())
                .build();
        final UserEntity repoRet = userRepository.save(user);
        final UserInfoDto userInfo = UserInfoDto.create(repoRet);
        return userInfo;
    }

    @Override
    public UserInfoDto getUserInfo(final UserInfoDto userInfoDto) {
        final UserEntity repoRet = userRepository.getById(userInfoDto.getUserId());
        final UserInfoDto userInfo = UserInfoDto.create(repoRet);
        return userInfo;
    }
}
