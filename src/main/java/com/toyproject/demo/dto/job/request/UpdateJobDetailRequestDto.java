package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateJobDetailRequestDto {

    @NotNull
    private Long jobId;

    @NotNull
    private String toDo;

    @NotNull
    private String jobDetail;

    public UpdateJobDetailRequestDto(@NonNull Long jobId,@NonNull String toDo,@NonNull String jobDetail) {
        this.jobId = jobId;
        this.toDo = toDo;
        this.jobDetail = jobDetail;
    }
}
