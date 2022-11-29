package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class AddJobWorkerRequestDto {

    private Long jobId;

    private Long workerId;

    public AddJobWorkerRequestDto(@NonNull Long jobId,@NonNull Long workerId) {
        this.jobId = jobId;
        this.workerId = workerId;
    }
}
