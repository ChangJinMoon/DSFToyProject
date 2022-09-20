package com.toyproject.demo.service;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.service.member.MysqlMemberService;
import com.toyproject.demo.service.presonalproject.PersonalProjectServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PersonalProjectServiceImplTest {

    @Autowired
    PersonalProjectServiceImpl personalProjectService;
    @Autowired
    MysqlMemberService memberService;

    ServiceTestDomain serviceTestDomain = new ServiceTestDomain();

    @Test
        //@Rollback(value = false)
    void init() {
        //given
        Member member = serviceTestDomain.makeMember();
        memberService.save(member);

        PersonalPageAddRequestDto ppard = new PersonalPageAddRequestDto();
        ppard.setProjectName("test");
        ppard.setProjectDetails("for test");

        //when
        personalProjectService.addProject(member.getId(), ppard);
        personalProjectService.addProject(member.getId(), ppard);

        //then
        Message<List<ProjectDetail>> list = personalProjectService.init(member.getId());
        List<ProjectDetail> data = list.getData();
        ProjectDetail projectDetail = data.get(0);

        Assertions.assertThat(projectDetail.getMembers().get(0).getMember().getId()).isEqualTo(member.getId());
        assertThat(data.size()).isEqualTo(2);
    }

    @Test
    @Rollback
    void addProject(){
        //given
        Member member = serviceTestDomain.makeMember();
        memberService.save(member);

        PersonalPageAddRequestDto personalPageAddRequestDto = new PersonalPageAddRequestDto();
        personalPageAddRequestDto.setProjectName("test");
        personalPageAddRequestDto.setProjectDetails("for test");

        personalProjectService.addProject(member.getId(),personalPageAddRequestDto);

        //when
        List<ProjectDetail> data = personalProjectService.init(member.getId()).getData();

        //then
        assertThat(data.get(0).getProjectLeader()).isEqualTo(member.getId());
        assertThat(data.size()).isEqualTo(1);
    }
}