package com.jy.cardme.service;

import com.jy.cardme.dao.UserRepository;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<UserEntity> optional = userRepository.findById(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return optional.get();
    }
}
