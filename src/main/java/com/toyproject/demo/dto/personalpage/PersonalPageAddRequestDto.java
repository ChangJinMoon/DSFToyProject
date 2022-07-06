package com.toyproject.demo.dto.personalpage;

import com.toyproject.demo.domain.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PersonalPageAddRequestDto {
    private String projectName;
    private String projectDetails;
    private String projectLeader;

    public Project getProject(){
        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectDetail(projectDetails);
        project.setProjectLeader(Integer.parseInt(projectLeader));
        project.setMemberList(new ArrayList<>());
        project.getMemberList().add(Integer.parseInt(projectLeader));
        return project;
    }
}
