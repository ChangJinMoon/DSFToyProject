package com.toyproject.demo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MakeHashKey {
    public String makeProjectHashKey(int repositorySize){
        StringBuilder sb = new StringBuilder();
        sb.append("P");
        sb.append(repositorySize+1);
        return sb.toString();
    }

    public String makeInviteKey(String projectId, int id){
        int h = new StringBuilder().append(projectId).append(Integer.toString(id)).toString().hashCode();
        return Integer.toString(h);
    }
}
