package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class DeleteJobRequestDto {

    private Long jobId;

    public DeleteJobRequestDto(@NonNull Long jobId) {
        this.jobId = jobId;
    }
}
