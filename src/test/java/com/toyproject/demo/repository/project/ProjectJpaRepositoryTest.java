package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProjectJpaRepositoryTest {

    @Autowired ProjectJpaRepository projectJpaRepository;

    @Test
    @Rollback(value = false)
    void save() {
        //member
        Member member = new Member();
        MemberProject memberProject = new MemberProject().createMemberProject(member);
        ProjectDetail projectDetail = new ProjectDetail().createProject("test","fortest",1L,memberProject);

        projectJpaRepository.save(projectDetail);
    }

    @Test
    void findProject() {

    }

    @Test
    void findAllProject() {

    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }
}