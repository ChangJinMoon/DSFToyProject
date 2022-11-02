package com.toyproject.demo.dto.projectDetail;

import lombok.*;

@Getter
@NoArgsConstructor
public class ProjectDetailDeleteRequestDto {
    private Long userId;
    private Long projectId;
    private Long sprintId;

    @Builder
    public ProjectDetailDeleteRequestDto(@NonNull Long userId,@NonNull Long projectId,@NonNull Long sprintId) {
        this.userId = userId;
        this.projectId = projectId;
        this.sprintId = sprintId;
    }
}
