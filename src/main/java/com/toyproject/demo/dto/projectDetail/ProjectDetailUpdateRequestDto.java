package com.toyproject.demo.dto.projectDetail;

import lombok.*;

@Getter
@NoArgsConstructor

public class ProjectDetailUpdateRequestDto {
    private Long userId;
    private Long sprintId;
    private String sprintName;
    private String sprintDetail;

    @Builder
    public ProjectDetailUpdateRequestDto(@NonNull Long userId,@NonNull Long sprintId,@NonNull String sprintName,@NonNull String sprintDetail) {
        this.userId = userId;
        this.sprintId = sprintId;
        this.sprintName = sprintName;
        this.sprintDetail = sprintDetail;
    }
}
