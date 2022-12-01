package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class AddJobWorkersRequestDto {

    @NonNull
    private Long jobId;

    @NotNull
    private List<Long> workerList;

    public AddJobWorkersRequestDto(@NonNull Long jobId,@NonNull List<Long> workerList) {
        this.jobId = jobId;
        this.workerList = workerList;
    }
}
