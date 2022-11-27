package com.toyproject.demo.domain.common;


public class SessionKey {
    public String makeProjectSessionKey(Long id){
        return "P"+ id.toString();
    }

    public String makeSprintSessionKey(Long id){
        return "S" + id.toString();
    }
}
