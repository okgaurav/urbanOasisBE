package com.mongo.backend.error;

import com.mongo.backend.config.ResponseCode;

import java.util.Date;

public class ErrorResponse {
    private ResponseCode code;
    private String details;

    public ErrorResponse(String resourceNotFound, int value, Date date) {
    }
}
