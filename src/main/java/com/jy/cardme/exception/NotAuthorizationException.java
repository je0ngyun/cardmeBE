package com.jy.cardme.exception;

public class NotAuthorizationException extends RuntimeException {
    private String s;

    public NotAuthorizationException(String s) {
        super(s);
        this.s = s;
    }
}
