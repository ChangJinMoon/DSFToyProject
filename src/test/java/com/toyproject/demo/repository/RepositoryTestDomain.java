package com.toyproject.demo.repository;

import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;

public class RepositoryTestDomain {

    public Member makeMember(){
        Member member = new Member();
        member.setEmail("jin1004boy@naver.com");
        member.setName("test");
        member.setPassword("1234");
        member.setFindPasswordAnswer("none");
        return member;
    }

    public ProjectDetail makProjectDetail(Member member){
        ProjectDetail projectDetail = new ProjectDetail();
        MemberProject memberProject = new MemberProject();

        memberProject.createMemberProject(member);
        return projectDetail.createProject("test","for test",
                member.getId(),memberProject.createMemberProject(member));
    }

    public Sprint makeSprint(ProjectDetail projectDetail){
        return Sprint.createSprint("sprinttest","fortest",projectDetail);
    }
}
