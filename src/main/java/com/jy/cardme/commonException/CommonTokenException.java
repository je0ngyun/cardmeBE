package com.jy.cardme.commonException;


public class CommonTokenException extends RuntimeException {
    private String s;
    public CommonTokenException(String s){
        super(s);
        this.s = s;
    }
}
