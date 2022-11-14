package com.toyproject.demo.dto.member;

import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberNameWithEmail {
    private Long userId;
    private String name;
    private String email;

    public MemberNameWithEmail(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MemberNameWithEmail(MemberProject memberProject){
        this.userId = memberProject.getMember().getId();
        this.name = memberProject.getMember().getName();
        this.email = memberProject.getMember().getEmail();
    }
}
