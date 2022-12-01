package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.dto.job.JobDto;
import lombok.Getter;

@Getter
public class AddJobWorkerResponseDto {

    private JobDto jobDto;

    public AddJobWorkerResponseDto(JobDto jobDto) {
        this.jobDto = jobDto;
    }
}
