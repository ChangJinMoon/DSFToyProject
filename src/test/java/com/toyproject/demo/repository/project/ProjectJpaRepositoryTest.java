package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.repository.RepositoryTestDomain;
import com.toyproject.demo.service.member.MysqlMemberService;
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
class ProjectJpaRepositoryTest {

    @Autowired ProjectJpaRepository projectJpaRepository;
    @Autowired
    MysqlMemberService memberService;
    RepositoryTestDomain repositoryTestDomain = new RepositoryTestDomain();

    @Test
    @Rollback(value = false)
    void save() {
        //given
        Member member = repositoryTestDomain.makeMember();
        memberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        //when
        projectJpaRepository.save(projectDetail);

        //then
        System.out.println("projectDetail.getProjectId() = " + projectDetail.getProjectId());
        System.out.println("projectDetail.getMembers().get(0).getProject().getProjectId() = " + projectDetail.getMembers().get(0).getProject().getProjectId());
        assertThat(projectDetail.getMembers().get(0).getProject().getProjectId()).isEqualTo(projectDetail.getProjectId());

    }

    @Test
    @Rollback(value = false)
    void findProject() {
        //given
        Member member = repositoryTestDomain.makeMember();
        memberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        ProjectDetail projectDetail2 = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail2);

        // when
        Optional<ProjectDetail> findOne = projectJpaRepository.findProject(2L);
        //then
        assertThat(findOne.get().getProjectId()).isEqualTo(projectDetail2.getProjectId());
    }

    @Test
    void findAllProject() {
        //given
        Member member = repositoryTestDomain.makeMember();
        memberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        ProjectDetail projectDetail2 = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail2);

        //when
        List<ProjectDetail> projectDetails = projectJpaRepository.findAllProject(member.getId()).get();

        //then
        assertThat(projectDetails.size()).isEqualTo(2);
        assertThat(projectDetails.get(0).getProjectId()).isEqualTo(1L);
        assertThat(projectDetails.get(1).getProjectId()).isEqualTo(2L);
        assertThat(projectDetails.get(0).getProjectLeader()).isEqualTo(1L);
        assertThat(projectDetails.get(0).getMembers().get(0).getMember().getId()).isEqualTo(1L);
    }

    @Test
    @Rollback(value = false)
    void update() {
        //given
        Member member = repositoryTestDomain.makeMember();
        memberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        //when
        ProjectDetail findOne = projectJpaRepository.findProject(projectDetail.getProjectId()).get();
        findOne.setProjectDetail("changed");
        projectJpaRepository.update(findOne);

        //then
        ProjectDetail changedOne = projectJpaRepository.findProject(projectDetail.getProjectId()).get();
        assertThat(changedOne.getProjectDetail()).isEqualTo("changed");
    }

    @Test
    @Rollback(value = false )
    void delete() {
        //given
        Member member = repositoryTestDomain.makeMember();
        memberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        //when
        ProjectDetail findOne = projectJpaRepository.findProject(projectDetail.getProjectId()).get();
        projectJpaRepository.delete(findOne.getProjectId());

        Optional<ProjectDetail> deletedOne = projectJpaRepository.findProject(findOne.getProjectId());

        //then
        assertThat(deletedOne.isPresent()).isEqualTo(false);
    }
}