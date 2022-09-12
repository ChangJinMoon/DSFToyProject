package com.toyproject.demo.service.projectDetail;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.invite.InviteRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailAddRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailDeleteRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailInitRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailUpdateRequestDto;

import java.util.List;

public interface ProjectDetailService {
    Message<List<Sprint>> init(Long projectId);

    Message<ProjectDetail> addMemberToProject(Long userId,Long projectId);

    Message<ProjectDetail> updateProject(Long projectId, PersonalPageUpdateRequestDto personalPageUpdateRequestDto);

    Message<String> deleteProject(Long projectId);

    Message<ProjectDetail> deleteMemberFromProject(Long userId, Long projectId);

    Message<String> addSprint(Long projectId, ProjectDetailAddRequestDto requestDto);

    Message<String> makeInviteCode(Long userId, InviteRequestDto requestDto);
}
