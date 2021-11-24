package com.jy.cardme.controller;

import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.components.commons.StatusCode;
import com.jy.cardme.dto.UserDto;
import com.jy.cardme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/cardme/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserInfo(@PathVariable("userId") String userId) {
        final UserDto.Info data = userService.getUserInfo(
                UserDto.Info.builder().userId(userId).build());
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.READ_USER)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity withdrawal(@ModelAttribute @Valid final UserDto.WithdrawalReq userWithdrawalReq){
        final UserDto.Info data = userService.withdrawal(userWithdrawalReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.DELETE_USER)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res,HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody @Valid final UserDto.UpdateReq userUpdateReq){
        final UserDto.Info data = userService.updateUser(userUpdateReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.UPDATE_USER)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res,HttpStatus.OK);
    }

    @PutMapping("pw")
    public ResponseEntity changePw(@RequestBody @Valid final UserDto.ChangePwReq userChangePwReq){
        final UserDto.Info data = userService.changePw(userChangePwReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.UPDATE_USER)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res,HttpStatus.OK);
    }
}