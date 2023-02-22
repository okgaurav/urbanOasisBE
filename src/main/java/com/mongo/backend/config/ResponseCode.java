package com.mongo.backend.config;

public enum ResponseCode{
    CREATED(200,"Created"),
    PARTIAL_CONTENT(206,"Partial Content"),
    INVALID_CONTENT(400, "Invalid request payload"),
    NOT_FOUND(404, "Element Not Found");





    private final String status;
    private final Integer code;
    ResponseCode(Integer code,String status) {
        this.status = status;
        this.code = code;
    }
    public String getStatus() {
        return status;
    }
    public Integer getCode() {
        return code;
    }
}
