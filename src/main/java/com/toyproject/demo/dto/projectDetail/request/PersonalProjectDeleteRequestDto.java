package com.toyproject.demo.dto.projectDetail.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor

public class PersonalProjectDeleteRequestDto {
    private Long projectId;

    @Builder
    public PersonalProjectDeleteRequestDto(@NonNull Long projectId) {
        this.projectId = projectId;
    }
}
