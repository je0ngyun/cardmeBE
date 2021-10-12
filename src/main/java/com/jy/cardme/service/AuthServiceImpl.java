package com.jy.cardme.service;

import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity pwEncryption(UserEntity user) {
        String encodedPw = passwordEncoder.encode(user.getUserPw());
        user.setUserPw(encodedPw);
        return user;
    }
}
