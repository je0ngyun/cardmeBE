package com.jy.cardme.controller;

import com.jy.cardme.components.DefaultRes;
import com.jy.cardme.components.ResponseMessage;
import com.jy.cardme.components.StatusCode;
import com.jy.cardme.dto.UserDto;
import com.jy.cardme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/")

public class RootController {
    private final UserService userService;
    private final Environment environment;

    @Autowired
    public RootController(UserService userService, Environment environment) {
        this.userService = userService;
        this.environment = environment;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid final UserDto.SignUpReq userSignUpReq) {
        final Object data = userService.signUp(userSignUpReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.CREATED_USER)
                .httpStatus(StatusCode.CREATED)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data)
                .build();
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody final UserDto.SignInReq userSignInReq) {
        final Object data = userService.signIn(userSignInReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.LOGIN_SUCCESS)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data)
                .build();
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
