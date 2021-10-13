package com.jy.cardme.commonException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private String s;
}
