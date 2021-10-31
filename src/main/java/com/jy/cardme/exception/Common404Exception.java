package com.jy.cardme.exception;

public class Common404Exception extends RuntimeException {
    private String s;

    public Common404Exception(String s) {
        super(s);
        this.s = s;
    }
}
