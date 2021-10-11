package com.jy.cardme.controller;

import com.jy.cardme.commonException.RestException;
import com.jy.cardme.components.DefaultRes;
import com.jy.cardme.components.ResponseMessage;
import com.jy.cardme.dto.UserInfoDto;
import com.jy.cardme.dto.UserSignUpDto;
import com.jy.cardme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid final UserSignUpDto userSignUpDto) {
        final HttpStatus httpStatus = HttpStatus.CREATED;
        final Object data = userService.signUp(userSignUpDto);
        final DefaultRes res = DefaultRes.builder().message(ResponseMessage.CREATED_USER).httpStatus(httpStatus).data(data).build();
        return new ResponseEntity(res, httpStatus);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserInfo(@PathVariable("userId") String userId) {
        final HttpStatus httpStatus = HttpStatus.OK;
        final Object data = userService.getUserInfo(
                UserInfoDto.builder().userId(userId).build());
        final DefaultRes res = DefaultRes.builder().message(ResponseMessage.READ_USER).httpStatus(httpStatus).data(data).build();
        return new ResponseEntity(res, httpStatus);
    }

    @GetMapping("/errortest")
    public String test() {
        throw new RestException();
    }

    @GetMapping("/test")
    public String test2() {
        return "ci/cd test in deploy master";
    }
}
