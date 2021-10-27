package com.jy.cardme.exception;


public class CommonTokenException extends RuntimeException {
    private String s;
    public CommonTokenException(String s){
        super(s);
        this.s = s;
    }
}
