package com.jy.cardme.controller;

import com.jy.cardme.commonException.RestException;
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
    public ResponseEntity<UserInfoDto> signUp(@RequestBody @Valid final UserSignUpDto userSignUpDto) {
        return new ResponseEntity<>(userService.signUp(userSignUpDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.getUserInfo(
                UserInfoDto.builder().userId(userId).build()),
                HttpStatus.OK
        );
    }

    @GetMapping("/errortest")
    public String test(){
        throw new RestException();
    }

    @GetMapping("/test")
    public String test2(){
        return "ci/cd test in deploy branch";
    }
}
