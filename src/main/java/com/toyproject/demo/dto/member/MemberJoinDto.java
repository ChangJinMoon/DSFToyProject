package com.toyproject.demo.dto.member;

import com.toyproject.demo.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinDto {
    private String name;
    private String email;
    private String password;
    private String findPasswordAnswer;

    public Member DtoToEntity(MemberJoinDto memberJoinDto){
        Member member = new Member();
        member.setName(memberJoinDto.getName());
        member.setEmail(memberJoinDto.getEmail());
        member.setPassword(memberJoinDto.getPassword());
        member.setFindPasswordAnswer(memberJoinDto.getFindPasswordAnswer());
        return member;
    }
}
