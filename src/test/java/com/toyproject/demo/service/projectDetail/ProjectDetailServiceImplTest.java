package com.toyproject.demo.service.projectDetail;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.dto.projectDetail.request.PersonalProjectGetOneRequestDto;
import com.toyproject.demo.dto.projectDetail.response.PersonalProjectGetOneResponseDto;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
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
        //given
        Member member = std.makeMember();
        memberService.save(member);

        PersonalPageAddRequestDto addRequestDto = new PersonalPageAddRequestDto(member.getId(), "test", "for test");
        personalProjectService.addProject(member.getId(), addRequestDto);
        List<ProjectDetail> allProject = projectJpaRepository.findAllProject(member.getId()).get();

        //when
        PersonalProjectGetOneRequestDto dto = new PersonalProjectGetOneRequestDto(allProject.get(0).getProjectId());
        PersonalProjectGetOneResponseDto data = projectDetailService.getOne(member.getId()).getData();

        //then
        assertThat(allProject.get(0).getProjectName()).isEqualTo(data.getProjectName());
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
    void addSprint() {
        Member member = std.makeMember();
        memberService.save(member);
    }
    //not yet
    @Test
    void makeInviteCode() {

    }
}
