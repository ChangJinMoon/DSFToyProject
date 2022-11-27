package com.toyproject.demo.domain.job;

import com.toyproject.demo.domain.MemberJob;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.repository.member.MemoryMemberRepository;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: 2022/11/19 create making Job Object method

@Entity
@Table(name = "job")
@Getter
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    private String toDo;

    private String detail;

    private LocalDateTime start;

    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sprint_id")
    private JobList jobList;

    @OneToMany(mappedBy = "jobToDo", cascade = CascadeType.ALL)
    private List<MemberJob> jobWorkers = new ArrayList<>();

    public static Job createJob(String toDo, String detail, List<Member> workers
            , LocalDateTime start, LocalDateTime end){
        Job temp = new Job();
        temp.toDo = toDo;
        temp.detail = detail;
        workers.stream().forEach(worker-> temp.jobWorkers.add(MemberJob.createMemberJob(worker,temp)));
        temp.start = start;
        temp.end = end;
        return temp;
    }

    public void updateJob(@Nullable String toDo,@Nullable String detail
            ,@Nullable LocalDateTime start,@Nullable LocalDateTime end){
        this.toDo = (toDo == null ? this.toDo : toDo);
        this.detail = (detail == null ? this.detail : detail);
        this.start = (start == null ? this.start : start);
        this.end = (end == null ? this.end : end);
    }

    public void addJobWork(Member member) {
        this.jobWorkers.add(MemberJob.createMemberJob(member,this));
    }

    public void addJobWorkByGroup(List<Member> workers){
        workers.stream().forEach(worker-> this.jobWorkers.add(MemberJob.createMemberJob(worker,this)));
    }
}
