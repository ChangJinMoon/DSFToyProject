package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.dto.job.JobDto;
import lombok.Getter;

@Getter
public class SaveJobResponseDto {

    private JobDto savedJob;

    public SaveJobResponseDto(JobDto savedJob) {
        this.savedJob = savedJob;
    }
}
