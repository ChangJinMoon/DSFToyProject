package com.toyproject.demo.dto.job;

import com.toyproject.demo.domain.job.Job;
import com.toyproject.demo.dto.member.MemberNameWithEmail;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class JobDto {

    private Long jobId;

    private String toDo;

    private String detail;

    private Date start;

    private Date end;

    private List<MemberNameWithEmail> jobWorkers = new ArrayList<>();

    public static JobDto jobToJobDto(Job job){
        JobDto temp = new JobDto();
        temp.jobId = job.getId();
        temp.toDo = job.getToDo();
        temp.detail = job.getDetail();
        temp.start = job.getStart();
        temp.end = job.getEnd();
        job.getJobWorkers().stream().forEach(member-> temp.jobWorkers.add(
                new MemberNameWithEmail(member.getWorker().getName(),member.getWorker().getEmail())));
        return temp;
    }

    @Override
    public String toString() {
        return "JobDto{" +
                "jobId=" + jobId +
                ", toDo='" + toDo + '\'' +
                ", detail='" + detail + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", jobWorkers=" + jobWorkers +
                '}';
    }
}
