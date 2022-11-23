package com.toyproject.demo.service;

import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.job.JobList;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailAddRequestDto;

public class ServiceTestDomain {
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

    public Sprint makeSprint(ProjectDetail projectDetail,int sprintType){
        Sprint temp;
        if(sprintType == 1)
            temp = new Code();
        else
            temp = new JobList();

        return temp.createSprint("sprinttest","fortest",projectDetail);
    }

    public PersonalPageAddRequestDto makePPARD(){
        PersonalPageAddRequestDto pageAddRequestDto = new PersonalPageAddRequestDto();

        return pageAddRequestDto;
    }

    public ProjectDetailAddRequestDto makeProjectDetailAddRequestDto(){
        ProjectDetailAddRequestDto projectDetailAddRequestDto = new ProjectDetailAddRequestDto();

        return projectDetailAddRequestDto;
    }

    public PersonalPageUpdateRequestDto makePersonalPageUpdateRequestDto
            (){
        PersonalPageUpdateRequestDto personalPageUpdateRequestDto = new PersonalPageUpdateRequestDto();

        return personalPageUpdateRequestDto;
    }

}
