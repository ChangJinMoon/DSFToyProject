package com.toyproject.demo.header;

import org.springframework.http.MediaType;

import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;

public class RestApiHeader {

    public static HttpHeaders makeJsonHeader(){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return headers;
    }

}
