package com.toyproject.demo.dto.personalpage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor

public class PersonalPageUpdateRequestDto {
    private Long projectId;
    private String projectName;
    private String projectDetails;

    @Builder
    public PersonalPageUpdateRequestDto(@NonNull Long projectId,@NonNull String projectName,@NonNull String projectDetails) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDetails = projectDetails;
    }
}
