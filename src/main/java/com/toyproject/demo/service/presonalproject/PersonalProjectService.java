package com.toyproject.demo.service.presonalproject;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageInitDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;

import java.util.List;

public interface PersonalProjectService {
    Message<List<PersonalPageInitDto>> init(Long userid);

    Message<ProjectDetail> addProject(Long userId, PersonalPageAddRequestDto projectPreview);
}
