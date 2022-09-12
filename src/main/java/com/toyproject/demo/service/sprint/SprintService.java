package com.toyproject.demo.service.sprint;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.projectDetail.ProjectDetailDeleteRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailUpdateRequestDto;

public interface SprintService {

    Message<Sprint> init(Long sprintId);
    Message<String> deleteSprint(Long userId, ProjectDetailDeleteRequestDto requestDto);
    Message<String> updateSprint(Long userId, ProjectDetailUpdateRequestDto requestDto);
}
