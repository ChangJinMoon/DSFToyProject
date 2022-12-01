package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DeleteJobWorkerRequestDto {

    @NotNull
    private Long jobId;

    @NotNull
    private Long workerId;

    public DeleteJobWorkerRequestDto(@NonNull Long jobId, @NonNull Long workerId) {
        this.workerId = workerId;
        this.jobId = jobId;
    }
}
