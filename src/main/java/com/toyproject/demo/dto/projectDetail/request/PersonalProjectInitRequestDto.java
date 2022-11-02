package com.toyproject.demo.dto.projectDetail.request;

import lombok.*;

@Getter
@NoArgsConstructor

public class PersonalProjectInitRequestDto {
    private Long projectId;

    @Builder
    public PersonalProjectInitRequestDto(@NonNull Long projectId) {
        this.projectId = projectId;
    }
}
