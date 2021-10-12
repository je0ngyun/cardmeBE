package com.jy.cardme.controller;

import com.jy.cardme.components.DefaultRes;
import com.jy.cardme.components.ResponseMessage;
import com.jy.cardme.components.StatusCode;
import com.jy.cardme.dto.UserSignUpDto;
import com.jy.cardme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public RootController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid final UserSignUpDto userSignUpDto) {
        final Object data = userService.signUp(userSignUpDto);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.CREATED_USER)
                .httpStatus(StatusCode.CREATED)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
}
