package com.jy.cardme.controller;

import com.jy.cardme.commonException.CommonTokenException;
import com.jy.cardme.components.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @GetMapping("/jwt/wrong-type-token")
    public ResponseEntity wrongTypeToken(){
        throw new CommonTokenException(ResponseMessage.WRONG_TOKEN);
    }
    @GetMapping("/jwt/expired-token")
    public ResponseEntity expiredToken(){
        throw new CommonTokenException(ResponseMessage.EXPIRED_TOKEN);
    }
    @GetMapping("/jwt/unsupported-token")
    public ResponseEntity unsupportedToken(){
        throw new CommonTokenException(ResponseMessage.UNSUPPORTED_TOKEN);
    }
    @GetMapping("/jwt/wrong-token")
    public ResponseEntity wrongToken(){
        throw new CommonTokenException(ResponseMessage.WRONG_TOKEN);
    }
    @GetMapping("jwt/unknown-token-exception")
    public ResponseEntity unknownException(){
        throw new CommonTokenException(ResponseMessage.UNKNOWN_TOKEN_ERROR);
    }
}
