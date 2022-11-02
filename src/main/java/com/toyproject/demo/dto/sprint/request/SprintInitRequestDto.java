package com.toyproject.demo.dto.sprint.request;

import lombok.*;

@Getter
@NoArgsConstructor
public class SprintInitRequestDto {
    private Long sprintId;

    @Builder
    public SprintInitRequestDto(@NonNull Long sprintId){
        this.sprintId = sprintId;
    }
}
