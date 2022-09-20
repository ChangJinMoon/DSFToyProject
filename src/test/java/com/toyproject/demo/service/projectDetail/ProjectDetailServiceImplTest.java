package com.toyproject.demo.service.projectDetail;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.service.ServiceTestDomain;
import com.toyproject.demo.service.member.MysqlMemberService;
import com.toyproject.demo.service.presonalproject.PersonalProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<Sprint> data = projectDetailService.init(projectDetail.getProjectId()).getData();
        assertThat(data.size()).isEqualTo(2);
    }

    @Test
    void deleteProject() {
        //given
        Member member = std.makeMember();
        memberService.save(member);

        PersonalPageAddRequestDto pageAddRequestDto = std.makePPARD();
        personalProjectService.addProject(member.getId(),pageAddRequestDto);

        //when
        ProjectDetail projectDetail = personalProjectService.init(member.getId()).getData().get(0);
        Message<String> message = projectDetailService.deleteProject(projectDetail.getProjectId());

        //then
        assertThat(message.getStatusEum()).isEqualTo(StatusEnum.OK);
        assertThat(personalProjectService.init(member.getId()).getData()).isEmpty();
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

        Long projectId = personalProjectService.init(member.getId()).getData().get(0).getProjectId();

        //when
        PersonalPageUpdateRequestDto personalPageUpdateRequestDto = std.makePersonalPageUpdateRequestDto();
        projectDetailService.updateProject(projectId,personalPageUpdateRequestDto);

        //then
        assertThat(projectJpaRepository.findProject(projectId).get().getProjectDetail()).isEqualTo("changed details");
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
