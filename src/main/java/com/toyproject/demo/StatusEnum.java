package com.toyproject.demo;

public enum StatusEnum {
    OK(200,"Success"),
    BAD_REQUEST_AUTHORIZATION(400,"Authorization was wrong"),
    BAD_REQUEST_NOT_ENOUGH(401,"Need more Data"),
    NOT_FOUND(404,"Not Found"),
    INTERNAL_SERVER_ERROR(500,"SERVER ERROR");

    int statusCode;
    String code;

    StatusEnum(int statusCode, String code){
        this.statusCode = statusCode;
        this.code = code;
    }
}
