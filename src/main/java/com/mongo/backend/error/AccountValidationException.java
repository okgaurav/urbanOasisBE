package com.mongo.backend.error;

public class AccountValidationException extends Exception{
    public AccountValidationException(String str){
        super(str);
    }
}
