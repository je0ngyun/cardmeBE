package com.jy.cardme.exception;

public class Common400Exception extends RuntimeException {
    private String s;

    public Common400Exception(String s) {
        super(s);
        this.s = s;
    }
}
