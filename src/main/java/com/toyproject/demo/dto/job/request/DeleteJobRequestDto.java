package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DeleteJobRequestDto {

    @NotNull
    private Long jobId;

    public DeleteJobRequestDto(@NonNull Long jobId) {
        this.jobId = jobId;
    }
}
