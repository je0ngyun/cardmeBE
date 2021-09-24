package com.jy.hiSleep.controller;

import com.jy.hiSleep.dto.UserSignUpDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("test")
    public String test(@ModelAttribute UserSignUpDto signUp, BindingResult bindingResult) {
        System.out.println(signUp.toString());
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(c ->{
                System.out.println(c);
            });
        }
        return "test";
    }
}
