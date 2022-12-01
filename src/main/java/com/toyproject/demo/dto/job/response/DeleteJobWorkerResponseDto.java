package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.dto.job.JobDto;
import lombok.Getter;

@Getter
public class DeleteJobWorkerResponseDto {

    private JobDto jobDto;

    public DeleteJobWorkerResponseDto(JobDto jobDto) {
        this.jobDto = jobDto;
    }
}
