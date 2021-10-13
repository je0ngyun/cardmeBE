package com.jy.cardme.commonException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WrongPassWordException extends RuntimeException {
    private String s;
}
