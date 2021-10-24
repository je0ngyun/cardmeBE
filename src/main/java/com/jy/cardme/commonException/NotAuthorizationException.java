package com.jy.cardme.commonException;

public class NotAuthorizationException extends RuntimeException{
    private String s;
    public NotAuthorizationException(String s){
        super(s);
        this.s = s;
    }
}
