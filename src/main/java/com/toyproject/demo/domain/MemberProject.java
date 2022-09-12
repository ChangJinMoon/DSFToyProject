package com.toyproject.demo.domain;

import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class MemberProject {

    @Id @GeneratedValue
    @Column(name = "member_project_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id")
    private ProjectDetail project;

    public MemberProject createMemberProject(Member member){
        MemberProject memberProject = new MemberProject();
        memberProject.member = member;
        return memberProject;
    }

    public boolean findMember(Long memberId){
        if(member.getId() == memberId)
            return true;
        else
             return false;
    }
}
