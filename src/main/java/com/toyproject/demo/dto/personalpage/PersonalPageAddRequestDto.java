package com.toyproject.demo.dto.personalpage;

import lombok.*;

@Getter
@NoArgsConstructor

public class PersonalPageAddRequestDto {
    private Long userId;
    private String projectName;
    private String projectDetails;

    @Builder
    public PersonalPageAddRequestDto(@NonNull Long userId, @NonNull String projectName,@NonNull String projectDetails) {
        this.userId = userId;
        this.projectName = projectName;
        this.projectDetails = projectDetails;
    }
}
