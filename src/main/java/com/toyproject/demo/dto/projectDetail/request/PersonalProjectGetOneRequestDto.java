package com.toyproject.demo.dto.projectDetail.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class PersonalProjectGetOneRequestDto {
    private Long projectId;

    public PersonalProjectGetOneRequestDto(@NonNull Long projectId) {
        this.projectId = projectId;
    }
}
