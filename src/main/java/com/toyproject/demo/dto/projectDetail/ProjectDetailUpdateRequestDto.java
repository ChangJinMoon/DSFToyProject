package com.toyproject.demo.dto.projectDetail;

import lombok.Data;

@Data
public class ProjectDetailUpdateRequestDto {
    private Long projectId;
    private Long sprintId;
}
