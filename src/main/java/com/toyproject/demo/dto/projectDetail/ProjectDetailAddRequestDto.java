package com.toyproject.demo.dto.projectDetail;

import com.toyproject.demo.domain.sprint.Sprint;
import lombok.Data;

@Data
public class ProjectDetailAddRequestDto {
    private String sprintName;
    private String sprintDetails;
}
