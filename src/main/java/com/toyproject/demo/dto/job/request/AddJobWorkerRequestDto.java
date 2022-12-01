package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AddJobWorkerRequestDto {

    @NotNull
    private Long jobId;

    @NotNull
    private Long workerId;

    public AddJobWorkerRequestDto(@NonNull Long jobId,@NonNull Long workerId) {
        this.jobId = jobId;
        this.workerId = workerId;
    }
}
