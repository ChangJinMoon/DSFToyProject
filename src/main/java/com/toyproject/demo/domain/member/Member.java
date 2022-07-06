package com.toyproject.demo.domain.member;

import lombok.*;

@Getter
@Setter
@ToString
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String findPasswordAnswer;
}
