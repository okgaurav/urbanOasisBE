package com.mongo.backend.error;

public class CustomException extends RuntimeException{
    private int responseCode;
    public CustomException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
