package com.jy.cardme.controller;

import com.jy.cardme.components.DefaultRes;
import com.jy.cardme.components.ResponseMessage;
import com.jy.cardme.components.StatusCode;
import com.jy.cardme.dto.UserDto;
import com.jy.cardme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserInfo(@PathVariable("userId") String userId) {
        final Object data = userService.getUserInfo(
                UserDto.Info.builder().userId(userId).build());
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.READ_USER)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
