package com.toyproject.demo.service.sprint;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageInitDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailAddRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailDeleteRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailUpdateRequestDto;
import com.toyproject.demo.repository.sprint.SprintJpaRepository;
import com.toyproject.demo.service.ServiceTestDomain;
import com.toyproject.demo.service.member.MemberService;
import com.toyproject.demo.service.presonalproject.PersonalProjectServiceImpl;
import com.toyproject.demo.service.projectDetail.ProjectDetailServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SprintServiceImplTest {

    @Autowired SprintServiceImpl sprintService;
    @Autowired MemberService memberService;
    @Autowired PersonalProjectServiceImpl personalProjectService;
    @Autowired ProjectDetailServiceImpl projectDetailService;
    @Autowired SprintJpaRepository sprintJpaRepository;
    ServiceTestDomain serviceTestDomain = new ServiceTestDomain();

    @Test
    @Rollback(value = false)
    void init() {
        //given
        Sprint sprint = makeSprint();

        //when
        //Sprint found = sprintService.init(sprint.getId()).getData();

        //then
        //assertThat(found.getId()).isEqualTo(sprint.getId());
    }

    @Test
    void deleteSprint() {
        //given
        Sprint sprint = makeSprint();

        //when
        ProjectDetailDeleteRequestDto projectDetailDeleteRequestDto = new ProjectDetailDeleteRequestDto();
        sprintService.deleteSprint(1L,projectDetailDeleteRequestDto);

        //then
        assertThat(sprintService.init(1L).getData()).isNull();
    }

    @Test
    @Rollback(value = false)
    void updateSprint() {
        //given
        Sprint sprint = makeSprint();

        //when
        ProjectDetailUpdateRequestDto projectDetailUpdateRequestDto = new ProjectDetailUpdateRequestDto();
        sprintService.updateSprint(1L,projectDetailUpdateRequestDto);

        //then
        assertThat(sprintService.init(1L).getData().getSprintName()).isEqualTo("changed");
    }

    Sprint makeSprint(){
        Member member = serviceTestDomain.makeMember("jin1004boy@naver.com");
        memberService.save(member);

        PersonalPageAddRequestDto personalPageAddRequestDto = serviceTestDomain.makePPARD();
        personalProjectService.addProject(member.getId(), personalPageAddRequestDto).getData();
        List<PersonalPageInitDto> data = personalProjectService.init(member.getId()).getData();
        Long projectId = data.get(0).getProjectId();
        System.out.println(projectId);

        //when
        projectDetailService.addSprint(projectId,serviceTestDomain.makeProjectDetailAddRequestDto());

        return sprintJpaRepository.findAll(projectId).get().get(0);
    }
}