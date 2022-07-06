package com.toyproject.demo.repository;

import com.toyproject.demo.domain.MakeHashKey;
import com.toyproject.demo.domain.Project;
import com.toyproject.demo.repository.project.ProjectMemoryRepository;
import com.toyproject.demo.repository.project.ProjectRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectMemoryRepositoryTest {
    ProjectRepository repository = new ProjectMemoryRepository();
    MakeHashKey makeHashKey = new MakeHashKey();

    @Test
    void save() {
        //given
        Project project = new Project();
        project.setProjectName("Spring DI Project");
        ArrayList<Integer> memberList = new ArrayList<>();
        memberList.add(1010);
        project.setMemberList(memberList);
        project.setProjectId(makeHashKey.
                makeProjectHashKey(repository.getProjectSize()));
        //when
        repository.save(project);
        //then
        assertThat(true)
                .isEqualTo(repository.findAllProject(1010).get()
                    .stream().anyMatch(project1 -> project.equals(project1)));

        //case 2
        //when
        repository.save(project);
    }

    @Test
    void findAllProject() {
        int userId = 1004;
        Project project = new Project();
        project.setProjectName("temp");
        project.setMemberList(new ArrayList<>());
        project.getMemberList().add(userId);

        repository.save(project);

        //then
        assertThat(repository.findAllProject(1003).isPresent()).isEqualTo(true);
    }

    @Test
    void delete() {
        //given
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1010);
        Project project = new Project();
        project.setProjectName("project");
        project.setMemberList(list);
        project.setProjectId(makeHashKey.
                makeProjectHashKey(repository.getProjectSize()));
        //when
        repository.save(project);
        repository.delete(project.getProjectId());

        //then
        assertThat(repository.findAllProject(1010).get()).isEmpty();
    }

    @Test
    void update() {
        //given
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1010);

        Project project = new Project();
        project.setProjectName("project");
        project.setMemberList(list);
        project.setProjectId(makeHashKey.makeProjectHashKey(repository.getProjectSize()));
        repository.save(project);

        //when
        //case1 - update project Name
        Project project1 = new Project();
        project1.setProjectName("fixed - project1");
        project1.setMemberList(project.getMemberList());
        project1.setProjectId(project.getProjectId());
        repository.update(project1);

        assertThat("fixed - project1")
                .isEqualTo(repository.findAllProject(1010).get().get(0).getProjectName());
        //case2 - update project MemberList
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1010);
        list2.add(1011);
        project1.setMemberList(list2);
        repository.update(project1);

        assertThat(repository.findAllProject(1011).get().size()).isEqualTo(1);
    }

    @Test
    void findProject() {
        //given
        Project project = new Project();
        project.setProjectName("example");
        project.setProjectId(makeHashKey.makeProjectHashKey(repository.getProjectSize()));
        repository.save(project);

        assertThat(repository.findProject(project.getProjectId()).get()).isEqualTo(project);
    }

    @Test
    void checkDoubleSave(){
        Project project = new Project();
        project.setProjectName("example");
        project.setProjectId(makeHashKey.makeProjectHashKey(repository.getProjectSize()));
        repository.save(project);

        assertThat(false).isEqualTo(repository.save(project));
    }
}