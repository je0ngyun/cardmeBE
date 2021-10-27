package com.jy.cardme.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WrongPassWordException extends RuntimeException {
    private String s;
}
