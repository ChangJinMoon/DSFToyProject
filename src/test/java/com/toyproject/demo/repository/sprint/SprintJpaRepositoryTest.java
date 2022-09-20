package com.toyproject.demo.repository.sprint;

import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.repository.RepositoryTestDomain;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.service.member.MysqlMemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class SprintJpaRepositoryTest {

    @Autowired
    MysqlMemberService mysqlMemberService;
    @Autowired
    ProjectJpaRepository projectJpaRepository;
    @Autowired
    SprintJpaRepository sprintJpaRepository;

    RepositoryTestDomain repositoryTestDomain = new RepositoryTestDomain();

    @Test
    @Rollback(value = false)
    void save() {
        //given
        Member member = repositoryTestDomain.makeMember();
        mysqlMemberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        //when
        Sprint sprint = repositoryTestDomain.makeSprint(projectJpaRepository.findProject(projectDetail.getProjectId()).get());
        sprintJpaRepository.save(sprint);

        //then
        assertThat(sprintJpaRepository.find(sprint.getId()).get().getId()).isEqualTo(sprint.getId());
    }

    @Test
    @Rollback(value = false)
    void find() {
        //given
        Member member = repositoryTestDomain.makeMember();
        mysqlMemberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        Sprint sprint = repositoryTestDomain.makeSprint(projectDetail);
        sprintJpaRepository.save(sprint);

        Sprint sprint2 = repositoryTestDomain.makeSprint(projectDetail);
        sprintJpaRepository.save(sprint2);

        //when
        Optional<Sprint> foundOne = sprintJpaRepository.find(sprint2.getId());

        //then
        assertThat(foundOne.get().getId()).isEqualTo(sprint2.getId());
    }

    @Test
    void findAll() {
        //given
        Member member = repositoryTestDomain.makeMember();
        mysqlMemberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        Sprint sprint = repositoryTestDomain.makeSprint(projectDetail);
        sprintJpaRepository.save(sprint);

        Sprint sprint2 = repositoryTestDomain.makeSprint(projectDetail);
        sprintJpaRepository.save(sprint2);

        //when
        List<Sprint> sprints
        = sprintJpaRepository.findAll(projectDetail.getProjectId()).get();

        //then
        assertThat(sprints.size()).isEqualTo(2);
        assertThat(sprints.get(0).getId()).isEqualTo(1L);
        assertThat(sprints.get(1).getId()).isEqualTo(2L);
        System.out.println("sprints.get(0).getSprintName() = " + sprints.get(0).getSprintName());
    }

    @Test
    void update() {
        //given
        Member member = repositoryTestDomain.makeMember();
        mysqlMemberService.save(member);

        ProjectDetail projectDetail = repositoryTestDomain.makProjectDetail(member);
        projectJpaRepository.save(projectDetail);

        Sprint sprint = repositoryTestDomain.makeSprint(projectDetail);
        sprintJpaRepository.save(sprint);

        Sprint sprint2 = repositoryTestDomain.makeSprint(projectDetail);
        sprintJpaRepository.save(sprint2);

        //when


        //then
    }

    @Test
    void delete() {
        //given
        //when
        //then
    }

    @Test
    void testToString() {
        //given
        //when
        //then
    }
}