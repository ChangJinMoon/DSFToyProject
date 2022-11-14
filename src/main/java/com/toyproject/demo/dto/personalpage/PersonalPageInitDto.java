package com.toyproject.demo.dto.personalpage;

import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.member.MemberNameWithEmail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PersonalPageInitDto {
    private Long projectId;
    private String projectName;
    private String projectDetail;
    private Long projectLeader;
    private List<MemberNameWithEmail> memberList;

    public PersonalPageInitDto transPersonalPageInitDto(ProjectDetail projectDetail){
        PersonalPageInitDto personalPageInitDto = new PersonalPageInitDto();
        personalPageInitDto.projectId = projectDetail.getProjectId();
        personalPageInitDto.projectName = projectDetail.getProjectName();
        personalPageInitDto.projectDetail = projectDetail.getProjectDetail();
        personalPageInitDto.projectLeader = projectDetail.getProjectLeader();
        personalPageInitDto.memberList = new ArrayList<>();
        projectDetail.getMembers().stream()
                .forEach(memberProject -> personalPageInitDto.memberList.add(
                        new MemberNameWithEmail(memberProject)));
        return  personalPageInitDto;
    }
}
