package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.dto.job.JobDto;

public class SaveJobResponseDto {

    private JobDto savedWorker;

    public SaveJobResponseDto(JobDto savedWorker) {
        this.savedWorker = savedWorker;
    }
}
