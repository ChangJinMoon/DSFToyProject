package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.dto.job.JobDto;
import lombok.Getter;

@Getter
public class UpdateJobDetailResponseDto {

    private JobDto jobDto;

    public UpdateJobDetailResponseDto(JobDto jobDto) {
        this.jobDto = jobDto;
    }
}
