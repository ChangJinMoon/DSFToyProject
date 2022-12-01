package com.toyproject.demo.dto.job.response;

import com.toyproject.demo.domain.job.JobList;
import com.toyproject.demo.dto.job.JobDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JobInitResponseDto {

    private List<JobDto> list;

    public JobInitResponseDto createDto(JobList jobList){
        JobInitResponseDto temp = new JobInitResponseDto();
        temp.list = new ArrayList<>();
        jobList.getJobList().stream().forEach(job -> temp.list.add(JobDto.jobToJobDto(job)));
        return temp;
    }
}
