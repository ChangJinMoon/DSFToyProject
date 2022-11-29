package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.dto.job.JobDto;

public class DeleteJobResponseDto {

    private JobDto deletedJob;

    public DeleteJobResponseDto(JobDto deletedJob) {
        this.deletedJob = deletedJob;
    }
}
