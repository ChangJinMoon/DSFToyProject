package com.toyproject.demo.dto.member;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
    private String email;
    private String password;
}
