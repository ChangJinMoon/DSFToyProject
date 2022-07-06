package com.toyproject.demo.service;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.MakeHashKey;
import com.toyproject.demo.domain.Project;
import com.toyproject.demo.repository.project.ProjectMemoryRepository;
import com.toyproject.demo.service.presonalproject.PersonalProjectService;
import com.toyproject.demo.service.presonalproject.PersonalProjectServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonalProjectServiceImplTest {
    PersonalProjectService personalProjectService = new PersonalProjectServiceImpl(new ProjectMemoryRepository(),new MakeHashKey());
    @Test
    void init() {
        //given
        int userId = 1004;
        Project project = new Project();
        project.setProjectName("temp");
        project.setMemberList(new ArrayList<>());
        project.getMemberList().add(userId);

        personalProjectService.addProject(userId,project);
        //when
        Message<List<Project>> message = personalProjectService.init(userId);

        //then
        //assertThat(message.getStatusEum()).isEqualTo(StatusEnum.OK);
        assertThat(personalProjectService.init(1003).getStatusEum()).isEqualTo(StatusEnum.NOT_FOUND);
    }

    @Test
    void deleteProject() {
        ArrayList<Integer> list  = new ArrayList<>();
        list.add(1011);
        Project project = new Project();
        project.setProjectName("temp 1");
        project.setMemberList(list);

        personalProjectService.addProject(1011,project);
        personalProjectService.deleteProject(project.getProjectId());

        Object init = personalProjectService.init(1011);
        assertThat(init).isEqualTo(null);
    }

    @Test
    void addMemberToProject() {
        //given
        int userId = 1004;
        Project project = new Project();
        project.setProjectName("temp");
        project.setMemberList(new ArrayList<>());
        project.getMemberList().add(userId);

        personalProjectService.addProject(userId,project);
        //when
        Message<List<Project>> message = personalProjectService.addMemberToProject(1003, project.getProjectId());
        //then
        //assertThat(message.getStatusEum()).isEqualTo(StatusEnum.OK);
        assertThat(message.getStatusEum()).isEqualTo(StatusEnum.OK);
    }

    @Test
    void updateProject() {

    }

    @Test
    void deleteMemberFromProject() {
        //given
        int userId = 1004;
        Project project = new Project();
        project.setProjectName("temp");
        project.setMemberList(new ArrayList<>());
        project.getMemberList().add(userId);

        personalProjectService.addProject(userId,project);
        //when
        Message<List<Project>> message =
                personalProjectService.deleteMemberFromProject(1003, project.getProjectId());
        assertThat(message.getStatusEum()).isEqualTo(StatusEnum.OK);

        message = personalProjectService.init(userId);
        assertThat(message.getStatusEum()).isEqualTo(StatusEnum.OK);
    }

    @Test
    void addProject(){
        //given
        int userId = 1004;
        Project project = new Project();
        project.setProjectName("temp");
        project.setMemberList(new ArrayList<>());
        project.getMemberList().add(userId);

        Message<List<Project>> message = personalProjectService.addProject(userId, project);
        assertThat(message.getStatusEum()).isEqualTo(StatusEnum.OK);
    }
}