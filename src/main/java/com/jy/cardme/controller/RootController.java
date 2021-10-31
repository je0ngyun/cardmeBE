package com.jy.cardme.controller;

import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.components.commons.StatusCode;
import com.jy.cardme.dto.UserDto;
import com.jy.cardme.service.CardService;
import com.jy.cardme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/")

public class RootController {
    private final UserService userService;
    private final Environment environment;
    private final CardService cardService;

    @Autowired
    public RootController(UserService userService, Environment environment, CardService cardService) {
        this.userService = userService;
        this.environment = environment;
        this.cardService = cardService;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid final UserDto.SignUpReq userSignUpReq) {
        final UserDto.Info data = userService.signUp(userSignUpReq);
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
        final UserDto.SignInRes data = userService.signIn(userSignInReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.LOGIN_SUCCESS)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data)
                .build();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/svg-return-test")
    public ResponseEntity<String> test() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("image/svg+xml"));
        String cardSvg = cardService.generatingCard();
        return new ResponseEntity<>(cardSvg, headers, HttpStatus.OK);
    }
}
