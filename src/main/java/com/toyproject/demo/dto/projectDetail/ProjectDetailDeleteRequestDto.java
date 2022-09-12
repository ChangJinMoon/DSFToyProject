package com.toyproject.demo.dto.projectDetail;

import lombok.Data;

@Data
public class ProjectDetailDeleteRequestDto {
    private Long projectId;
    private Long sprintId;
}
