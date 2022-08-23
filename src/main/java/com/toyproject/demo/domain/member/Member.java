package com.toyproject.demo.domain.member;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String findPasswordAnswer;

    // Test를 위한 수정
}
