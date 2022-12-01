package com.toyproject.demo.dto.job.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class SaveJobRequestDto {

    @NotNull
    private Long sprintId;

    @NotNull
    private String toDo;

    @NotNull
    private String detail;

    @NotNull
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date start;

    @NotNull
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date end;

    private List<Long> workerList;

    public SaveJobRequestDto(@NonNull Long sprintId, @NonNull String toDo,@NonNull String detail
            ,@NonNull Date start,@NonNull Date end,@Nullable List<Long> workerList) {
        this.sprintId = sprintId;
        this.toDo = toDo;
        this.detail = detail;
        this.start = start;
        this.end = end;
        this.workerList = (workerList == null ? new ArrayList<>() : workerList);
    }

    @Override
    public String toString() {
        return "SaveJobRequestDto{" +
                "sprintId=" + sprintId +
                ", toDo='" + toDo + '\'' +
                ", detail='" + detail + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", workerList=" + workerList +
                '}';
    }
}
