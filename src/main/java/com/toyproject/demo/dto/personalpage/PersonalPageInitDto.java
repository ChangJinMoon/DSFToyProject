package com.toyproject.demo.dto.personalpage;

import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PersonalPageInitDto {
    private Long projectId;
    private String projectName;
    private String projectDetail;
    private Long projectLeader;
    private List<String> memberList;

    public PersonalPageInitDto transPersonalPageInitDto(ProjectDetail projectDetail){
        PersonalPageInitDto personalPageInitDto = new PersonalPageInitDto();
        personalPageInitDto.projectId = projectDetail.getProjectId();
        personalPageInitDto.projectName = projectDetail.getProjectName();
        personalPageInitDto.projectDetail = projectDetail.getProjectDetail();
        personalPageInitDto.projectLeader = projectDetail.getProjectLeader();
        personalPageInitDto.memberList = new ArrayList<>();
        projectDetail.getMembers().stream()
                .forEach(memberProject -> personalPageInitDto.memberList.add(memberProject.getMember().getName()));
        return  personalPageInitDto;
    }
}
