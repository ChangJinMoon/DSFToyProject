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

    public ProjectDetailAddRequestDto(@NonNull Long projectId, @NonNull String sprintName,@NonNull String sprintDetails,@NonNull int type) {
        this.projectId = projectId;
        this.sprintName = sprintName;
        this.sprintDetails = sprintDetails;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProjectDetailAddRequestDto{" +
                "projectId=" + projectId +
                ", sprintName='" + sprintName + '\'' +
                ", sprintDetails='" + sprintDetails + '\'' +
                ", type=" + type +
                '}';
    }
}
