package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class DeleteJobWorkerRequestDto {

    private Long jobId;

    private Long workerId;

    public DeleteJobWorkerRequestDto(@NonNull Long jobId, @NonNull Long workerId) {
        this.workerId = workerId;
        this.jobId = jobId;
    }
}
