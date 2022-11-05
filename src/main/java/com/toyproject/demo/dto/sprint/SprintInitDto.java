package com.toyproject.demo.dto.sprint;

import com.toyproject.demo.domain.sprint.Sprint;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SprintInitDto {
    private Long sprintId;
    private String sprintName;
    private String sprintDetail;
    private LocalDateTime localDateTime;

    public SprintInitDto transSprintInitDto(Sprint sprint){
        SprintInitDto sprintInitDto = new SprintInitDto();
        sprintInitDto.sprintId = sprint.getId();
        sprintInitDto.sprintName = sprint.getSprintName();
        sprintInitDto.sprintDetail = sprint.getSprintDetail();
        sprintInitDto.localDateTime = sprint.getLocalDateTime();
        return sprintInitDto;
    }
}
