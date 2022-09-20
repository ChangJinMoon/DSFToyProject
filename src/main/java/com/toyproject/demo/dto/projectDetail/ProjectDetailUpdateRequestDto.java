package com.toyproject.demo.dto.projectDetail;

import lombok.Data;

@Data
public class ProjectDetailUpdateRequestDto {
    private Long sprintId;
    private String sprintName;
    private String sprintDetail;
}
