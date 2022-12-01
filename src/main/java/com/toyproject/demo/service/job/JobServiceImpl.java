package com.toyproject.demo.service.job;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.MemberJob;
import com.toyproject.demo.domain.job.Job;
import com.toyproject.demo.domain.job.JobList;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.job.JobDto;
import com.toyproject.demo.dto.job.request.*;
import com.toyproject.demo.dto.job.response.*;
import com.toyproject.demo.repository.job.JobListRepository;
import com.toyproject.demo.repository.job.JobRepository;
import com.toyproject.demo.repository.member.MemberRepository;
import com.toyproject.demo.repository.memberJob.MemberJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobListRepository jobListRepository;
    private final JobRepository jobRepository;
    private final MemberRepository memberRepository;
    private final MemberJobRepository memberJobRepository;

    @Override
    public Message<JobInitResponseDto> init(Long sprintId) {
        Optional<JobList> result = jobListRepository.findJobListById(sprintId);
        Message<JobInitResponseDto> response = new Message<>();

        if(result.isEmpty()){
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("JobList is empty");
            return response;
        }

        response.setStatusEum(StatusEnum.OK);
        response.setData(new JobInitResponseDto().createDto(result.get()));
        return response;
    }

    @Override
    public Message<SaveJobResponseDto> saveJob(SaveJobRequestDto dto) {
        Optional<JobList> result = jobListRepository.findJobListById(dto.getSprintId());
        Message<SaveJobResponseDto> response = new Message<>();

        if(result.isEmpty()){
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("sprintId is wrong");
            return response;
        }
        //find member Object
        List<Member> workers = new ArrayList<>();
        dto.getWorkerList().stream()
                .forEach(worker -> workers.add(memberRepository.findMember(worker).get()));

        //add Job
        Job newJob = Job.createJob(dto.getToDo(),dto.getDetail(),workers
                ,dto.getStart(),dto.getEnd(),result.get());
        result.get().addJobOnlyOne(newJob);

        //update db
        jobListRepository.update(result.get());

        response.setStatusEum(StatusEnum.OK);
        response.setData(new SaveJobResponseDto(
                JobDto.jobToJobDto(result.get().recentUpdateJob())));
        return response;
    }

    @Override
    public Message<DeleteJobResponseDto> deleteJob(DeleteJobRequestDto dto) {
        Optional<Job> result = jobRepository.findJobById(dto.getJobId());
        Message<DeleteJobResponseDto> response = new Message<>();

        if(result.isEmpty()){
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("Job is not exist");
            return response;
        }

        jobRepository.delete(result.get());
        response.setStatusEum(StatusEnum.OK);
        response.setData(new DeleteJobResponseDto(JobDto.jobToJobDto(result.get())));
        return response;
    }

    @Override
    public Message<AddJobWorkerResponseDto> addWorker(AddJobWorkerRequestDto dto) {
        Optional<Job> result = jobRepository.findJobById(dto.getJobId());
        Message<AddJobWorkerResponseDto> response = new Message<>();

        if(result.isEmpty()){
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("Job is not exist");
            return response;
        }

        //find worker
        Optional<Member> member = memberRepository.findMember(dto.getWorkerId());
        if(member.isEmpty()){
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("MemberId is wrong");
            return response;
        }

        //worker add
        result.get().addJobWork(member.get());
        jobRepository.update(result.get());

        response.setStatusEum(StatusEnum.OK);
        response.setData(new AddJobWorkerResponseDto(JobDto.jobToJobDto(result.get())));
        return response;
    }

    @Override
    public Message<AddJobWorkerResponseDto> addWorkers(AddJobWorkersRequestDto dto) {
        Optional<Job> result = jobRepository.findJobById(dto.getJobId());
        Message<AddJobWorkerResponseDto> response = new Message<>();

        if(result.isEmpty()) {
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("Job is not exist");
            return response;
        }

        //find Wokers
        List<Member> workers = new ArrayList<>();
        dto.getWorkerList().forEach(workerId-> workers.add(memberRepository.findMember(workerId).get()));

        //add workers
        result.get().addJobWorkByGroup(workers);
        response.setStatusEum(StatusEnum.OK);
        response.setData(new AddJobWorkerResponseDto(JobDto.jobToJobDto(result.get())));
        return response;
    }

    @Override
    public Message<DeleteJobWorkerResponseDto> deleteWorker(DeleteJobWorkerRequestDto dto) {
        Optional<Job> result = jobRepository.findJobById(dto.getJobId());
        Message<DeleteJobWorkerResponseDto> response = new Message<>();

        if(result.isEmpty()) {
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("Job is not exist");
            return response;
        }

        //delete worker
        int idx = result.get().findWorkByMemberId(dto.getWorkerId());
        if(idx == -1) {
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("the worker was not assign this job");
            return response;
        }

        MemberJob removed = result.get().deleteJobWork(idx);
        memberJobRepository.deleteMemberJob(removed);
        response.setStatusEum(StatusEnum.OK);
        response.setData(new DeleteJobWorkerResponseDto(JobDto.jobToJobDto(result.get())));
        return response;
    }

    @Override
    public Message<UpdateJobDetailResponseDto> updateJobDetail(UpdateJobDetailRequestDto dto) {
        Optional<Job> result = jobRepository.findJobById(dto.getJobId());
        Message<UpdateJobDetailResponseDto> response = new Message<>();

        if(result.isEmpty()) {
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("Job is not exist");
            return response;
        }

        //update
        result.get().updateJob(dto.getToDo(),dto.getJobDetail(),null,null);
        jobRepository.update(result.get());

        response.setStatusEum(StatusEnum.OK);
        response.setData(new UpdateJobDetailResponseDto(JobDto.jobToJobDto(result.get())));
        return response;
    }

    @Override
    public Message<UpdateJobDateResponseDto> updateJobDate(UpdateJobDateRequestDto dto) {
        Optional<Job> result = jobRepository.findJobById(dto.getJobId());
        Message<UpdateJobDateResponseDto> response = new Message<>();

        if(result.isEmpty()) {
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("Job is not exist");
            return response;
        }

        //update
        result.get().updateJob(null,null,dto.getStart(),dto.getEnd());
        jobRepository.update(result.get());

        response.setStatusEum(StatusEnum.OK);
        response.setData(new UpdateJobDateResponseDto(JobDto.jobToJobDto(result.get())));
        return response;
    }
}
