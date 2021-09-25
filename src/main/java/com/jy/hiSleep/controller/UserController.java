package com.jy.hiSleep.controller;

import com.jy.hiSleep.dto.UserInfoDto;
import com.jy.hiSleep.dto.UserSignUpDto;
import com.jy.hiSleep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<UserInfoDto> test(@RequestBody @Valid final UserSignUpDto userSignUpDto) {
        return ResponseEntity.ok(userService.signUp(userSignUpDto));
    }
}
