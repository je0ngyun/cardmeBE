package com.jy.cardme.service;

import com.jy.cardme.commonException.WrongPassWordException;
import com.jy.cardme.dto.UserDto;
import com.jy.cardme.entity.UserEntity;
import com.jy.cardme.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserEntity pwEncrypting(UserEntity user) {
        String encodedPw = passwordEncoder.encode(user.getUserPw());
        user.setUserPw(encodedPw);
        return user;
    }

    @Override
    public String issuingToken(UserDto.SignInReq userSignInReq, UserEntity user) {
        if (!passwordEncoder.matches(userSignInReq.getUserPw(), user.getUserPw())) {
            throw new WrongPassWordException(user.getUserPw());
        }
        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }

}
