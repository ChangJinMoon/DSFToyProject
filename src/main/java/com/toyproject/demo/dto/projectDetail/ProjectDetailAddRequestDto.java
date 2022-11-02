package com.toyproject.demo.dto.projectDetail;

import lombok.*;

@Getter
@NoArgsConstructor

public class ProjectDetailAddRequestDto {
    private Long projectId;
    private String sprintName;
    private String sprintDetails;

    @Builder
    public ProjectDetailAddRequestDto(@NonNull Long projectId, @NonNull String sprintName,@NonNull String sprintDetails) {
        this.projectId = projectId;
        this.sprintName = sprintName;
        this.sprintDetails = sprintDetails;
    }
}
