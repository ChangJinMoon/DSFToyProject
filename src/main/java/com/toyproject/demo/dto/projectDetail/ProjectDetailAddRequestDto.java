package com.toyproject.demo.dto.projectDetail;

import lombok.*;

@Getter
@NoArgsConstructor

public class ProjectDetailAddRequestDto {
    private Long projectId;
    private String sprintName;
    private String sprintDetails;
    //add
    private int type;

    @Builder
    public ProjectDetailAddRequestDto(@NonNull Long projectId, @NonNull String sprintName,@NonNull String sprintDetails,@NonNull int type) {
        this.projectId = projectId;
        this.sprintName = sprintName;
        this.sprintDetails = sprintDetails;
        this.type = type;
    }
}
