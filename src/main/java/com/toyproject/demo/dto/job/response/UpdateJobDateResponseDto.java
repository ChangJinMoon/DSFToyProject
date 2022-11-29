package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.dto.job.JobDto;

public class UpdateJobDateResponseDto {

    private JobDto jobDto;

    public UpdateJobDateResponseDto(JobDto jobDto) {
        this.jobDto = jobDto;
    }
}
