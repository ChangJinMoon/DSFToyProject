package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SaveJobRequestDto {

    private Long sprintId;

    private String toDo;

    private String detail;

    private LocalDateTime start;

    private LocalDateTime end;

    private List<Long> workerList;

    public SaveJobRequestDto(@NonNull Long sprintId, @NonNull String toDo,@NonNull String detail
            ,@NonNull LocalDateTime start,@NonNull LocalDateTime end,@Nullable List<Long> workerList) {
        this.sprintId = sprintId;
        this.toDo = toDo;
        this.detail = detail;
        this.start = start;
        this.end = end;
        this.workerList = (workerList == null ? new ArrayList<>() : workerList);
    }
}
