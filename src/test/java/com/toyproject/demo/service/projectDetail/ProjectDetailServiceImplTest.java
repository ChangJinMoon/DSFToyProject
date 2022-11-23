package com.toyproject.demo.service.projectDetail;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailAddRequestDto;
import com.toyproject.demo.dto.projectDetail.request.PersonalProjectGetOneRequestDto;
import com.toyproject.demo.dto.projectDetail.response.PersonalProjectGetOneResponseDto;
import com.toyproject.demo.dto.sprint.SprintInitDto;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.repository.sprint.SprintRepository;
import com.toyproject.demo.service.ServiceTestDomain;
import com.toyproject.demo.service.member.MysqlMemberService;
import com.toyproject.demo.service.presonalproject.PersonalProjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProjectDetailServiceImplTest {

    @Autowired MysqlMemberService memberService;

    @Autowired
    PersonalProjectServiceImpl personalProjectService;

    @Autowired
    ProjectDetailServiceImpl projectDetailService;

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    SprintRepository sprintRepository;

    ServiceTestDomain std = new ServiceTestDomain();

    @Test
    @Rollback(value = false)
    void init() {
        //given
        Member member = std.makeMember();
        memberService.save(member);

        PersonalPageAddRequestDto personalPageAddRequestDto = std.makePPARD();
        personalProjectService.addProject(member.getId(), personalPageAddRequestDto).getData();
        ProjectDetail projectDetail = projectJpaRepository.findProject(member.getId()).get();

        //when
        projectDetailService.addSprint(projectDetail.getProjectId(),std.makeProjectDetailAddRequestDto());
        projectDetailService.addSprint(projectDetail.getProjectId(),std.makeProjectDetailAddRequestDto());

        //then
        //List<Sprint> data = projectDetailService.init(projectDetail.getProjectId()).getData();
        //assertThat(data.size()).isEqualTo(2);
    }

    @Test
    @Rollback(value = true)
    void findOne(){

    }

    @Test
    void deleteProject() {
        //given

        //when
        boolean One = projectJpaRepository.delete(5L);
        boolean two = projectJpaRepository.delete(5L);
        //then
        assertThat(One).isEqualTo(two);
    }
    //not yet
    @Test
    void addMemberToProject() {

    }

    @Test
    @Rollback(value = false)
    void updateProject() {
        //given
        Member member = std.makeMember();
        memberService.save(member);

        PersonalPageAddRequestDto pageAddRequestDto = std.makePPARD();
        personalProjectService.addProject(member.getId(),pageAddRequestDto);


        //when

        //then
    }

    @Test
    void deleteMemberFromProject() {

    }

    @Test
    @Rollback(value = false)
    void addSprint() {
        //given
        Member member = std.makeMember();
        memberService.save(member);

        ProjectDetail projectDetail = std.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        //add Code
        Sprint sprint = std.makeSprint(projectDetail, 1);
        projectDetailService.addSprint(projectDetail.getProjectId(),
                new ProjectDetailAddRequestDto(projectDetail.getProjectId(), sprint.getSprintName(), sprint.getSprintDetail(), 2));

        //add jpbList
        projectDetailService.addSprint(projectDetail.getProjectId(),
                new ProjectDetailAddRequestDto(projectDetail.getProjectId(), sprint.getSprintName(), sprint.getSprintDetail(), 1));

        //when
        Optional<List<Sprint>> resultList = sprintRepository.findAll(projectDetail.getProjectId());
        //then
        System.out.println(resultList.get().get(0).getClass());
        System.out.println(resultList.get().get(1).getClass());

    }
    //not yet
    @Test
    void makeInviteCode() {

    }
}
