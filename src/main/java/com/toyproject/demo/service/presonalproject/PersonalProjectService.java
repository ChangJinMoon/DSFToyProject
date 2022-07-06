package com.toyproject.demo.service.presonalproject;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.Project;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;

import java.util.List;

public interface PersonalProjectService {
    Message<List<Project>> init(int userid);

    Message<List<Project>> addProject(int userId, Project project);

    Message<List<Project>> deleteProject(String projectId);

    Message<List<Project>> addMemberToProject(int userId,String projectId);

    Message<List<Project>> updateProject(String projectId, PersonalPageUpdateRequestDto personalPageUpdateRequestDto);

    Message<List<Project>> deleteMemberFromProject(int userId, String projectId);

}
