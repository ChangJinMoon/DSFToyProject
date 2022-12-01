package com.toyproject.demo.service.job;

import com.toyproject.demo.Message;
import com.toyproject.demo.dto.job.request.*;
import com.toyproject.demo.dto.job.response.*;

public interface JobService {

    Message<JobInitResponseDto> init(Long sprintId);

    Message<SaveJobResponseDto> saveJob(SaveJobRequestDto dto);

    Message<DeleteJobResponseDto> deleteJob(DeleteJobRequestDto dto);

    Message<AddJobWorkerResponseDto> addWorker(AddJobWorkerRequestDto dto);

    Message<AddJobWorkerResponseDto> addWorkers(AddJobWorkersRequestDto dto);

    Message<DeleteJobWorkerResponseDto> deleteWorker(DeleteJobWorkerRequestDto dto);

    Message<UpdateJobDetailResponseDto> updateJobDetail(UpdateJobDetailRequestDto dto);

    Message<UpdateJobDateResponseDto> updateJobDate(UpdateJobDateRequestDto dto);

}
