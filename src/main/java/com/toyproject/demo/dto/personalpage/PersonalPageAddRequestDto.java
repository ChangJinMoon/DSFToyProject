package com.toyproject.demo.dto.personalpage;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor

public class PersonalPageAddRequestDto {
    @NotNull
    private Long userId;
    private String projectName;
    private String projectDetails;

    @Builder
    public PersonalPageAddRequestDto(@NonNull Long userId, @NonNull String projectName, @NonNull String projectDetails) {
        this.userId = userId;
        this.projectName = projectName;
        this.projectDetails = projectDetails;
    }

    @Override
    public String toString() {
        return "PersonalPageAddRequestDto{" +
                "userId=" + userId +
                ", projectName='" + projectName + '\'' +
                ", projectDetails='" + projectDetails + '\'' +
                '}';
    }
}
