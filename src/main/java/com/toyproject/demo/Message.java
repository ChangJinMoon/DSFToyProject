package com.toyproject.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Message <T>{
    private StatusEnum statusEum;
    private String message;
    private T data;

    public Message(StatusEnum statusEnum) {
        statusEum = statusEnum;
    }
    public Message() {
        this.statusEum = StatusEnum.BAD_REQUEST_AUTHORIZATION;
        this.data = null;
        this.message = null;
    }
}
