package com.toyproject.demo.domain;

import com.toyproject.demo.domain.member.Member;

import javax.persistence.*;

@Entity
public class MemberProject {

    @Id@GeneratedValue
    @Column(name = "member_project_id")

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
