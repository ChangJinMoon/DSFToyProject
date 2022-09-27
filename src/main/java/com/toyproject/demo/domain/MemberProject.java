package com.toyproject.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
public class MemberProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_project_id")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "project_id")
    private ProjectDetail project;

    public MemberProject createMemberProject(Member member){
        MemberProject memberProject = new MemberProject();
        memberProject.member = member;
        return memberProject;
    }

    public void setProject(ProjectDetail projectDetail){
        this.project = projectDetail;
    }

    public boolean findMember(Long memberId){
        if(member.getId() == memberId)
            return true;
        else
            return false;
    }
}