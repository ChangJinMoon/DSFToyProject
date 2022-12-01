package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.dto.job.request.*;
import com.toyproject.demo.dto.job.response.*;
import com.toyproject.demo.header.RestApiHeader;
import com.toyproject.demo.service.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/project/sprint/jobList")
    public ResponseEntity<Message> init(HttpServletRequest request){
        Message<JobInitResponseDto> result = jobService
                .init(Long.parseLong(request.getParameter("sprintId")));

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @PostMapping("/project/sprint/job")
    public ResponseEntity<Message> saveJob(@RequestBody @Valid SaveJobRequestDto dto){
        Message<SaveJobResponseDto> result = jobService.saveJob(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @DeleteMapping("project/sprint/job")
    public ResponseEntity<Message> deleteJob(@RequestBody @Valid DeleteJobRequestDto dto){
        Message<DeleteJobResponseDto> result = jobService.deleteJob(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @PostMapping("project/sprint/job/worker")
    public ResponseEntity<Message> addWorker(@RequestBody @Valid AddJobWorkerRequestDto dto){
        Message<AddJobWorkerResponseDto> result = jobService.addWorker(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @PostMapping("project/sprint/job/workers")
    public ResponseEntity<Message> addWorkers(@RequestBody @Valid AddJobWorkersRequestDto dto){
        Message<AddJobWorkerResponseDto> result = jobService.addWorkers(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @DeleteMapping("project/sprint/job/worker")
    public ResponseEntity<Message> deleteWorker(@RequestBody @Valid DeleteJobWorkerRequestDto dto) {
        Message<DeleteJobWorkerResponseDto> result = jobService.deleteWorker(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @PutMapping("project/sprint/job/detail")
    public ResponseEntity<Message> updateJobDetail(@RequestBody @Valid UpdateJobDetailRequestDto dto){
        Message<UpdateJobDetailResponseDto> result = jobService.updateJobDetail(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @PutMapping("project/sprint/job/date")
    public ResponseEntity<Message> updateJobDate(@RequestBody @Valid UpdateJobDateRequestDto dto) {
        Message<UpdateJobDateResponseDto> result = jobService.updateJobDate(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }
}
