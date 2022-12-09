package com.toyproject.demo.dto.projectDetail.response;

import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.member.MemberNameWithEmail;
import com.toyproject.demo.dto.projectDetail.request.PersonalProjectGetOneRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PersonalProjectGetOneResponseDto {
    private String projectName;
    private String projectDetail;
    private Long projectLeader;
    private List<MemberNameWithEmail> memberList;
    private Integer sprintSize;

    public PersonalProjectGetOneResponseDto projectToDto(ProjectDetail projectDetail){
        PersonalProjectGetOneResponseDto dto = new PersonalProjectGetOneResponseDto();
        dto.projectName = projectDetail.getProjectName();
        dto.projectDetail = projectDetail.getProjectDetail();
        dto.projectLeader = projectDetail.getProjectLeader();
        dto.memberList = new ArrayList<>();
        projectDetail.getMembers().stream().forEach(memberProject -> dto.memberList.add(new MemberNameWithEmail(memberProject)));
        dto.sprintSize = projectDetail.getSprintList().size();
        return dto;
    }
}
