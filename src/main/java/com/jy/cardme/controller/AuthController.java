package com.jy.cardme.controller;

import com.jy.cardme.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
}
