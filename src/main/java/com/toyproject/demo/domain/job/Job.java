package com.toyproject.demo.domain.job;

import com.toyproject.demo.domain.MemberJob;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.repository.member.MemoryMemberRepository;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//sql error -> 42001 - 212 : 필드에 예약어와 같은 이름이 사용되었을 때

@Entity
@Table(name = "job")
@Getter
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    private String toDo;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Column(name = "job_start")
    @Temporal(TemporalType.DATE)
    private Date start;

    @Column(name = "job_end")
    @Temporal(TemporalType.DATE)
    private Date end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sprint_id")
    private JobList jobList;

    @OneToMany(mappedBy = "jobToDo", cascade = CascadeType.ALL)
    private List<MemberJob> jobWorkers = new ArrayList<>();

    public static Job createJob(String toDo, String detail, List<Member> workers
            , Date start, Date end, JobList jobList){
        Job temp = new Job();
        temp.toDo = toDo;
        temp.detail = detail;
        workers.stream().forEach(worker-> temp.jobWorkers.add(MemberJob.createMemberJob(worker,temp)));
        temp.start = start;
        temp.end = end;
        temp.jobList = jobList;
        return temp;
    }

    public void updateJob(@Nullable String toDo,@Nullable String detail
            ,@Nullable Date start,@Nullable Date end){
        this.toDo = (toDo == null ? this.toDo : toDo);
        this.detail = (detail == null ? this.detail : detail);
        this.start = (start == null ? this.start : start);
        this.end = (end == null ? this.end : end);
    }

    public void addJobWork(Member member) {
        this.jobWorkers.add(MemberJob.createMemberJob(member,this));
        this.jobWorkers.size();
    }

    public void addJobWorkByGroup(List<Member> workers){
        workers.stream().forEach(worker-> this.jobWorkers.add(MemberJob.createMemberJob(worker,this)));
    }

    public MemberJob deleteJobWork(int idx){
        return jobWorkers.remove(idx);
    }

    public int findWorkByMemberId(Long memberId){
        for(int i = 0; i < jobWorkers.size(); i++){
            if(jobWorkers.get(i).getWorker().getId() == memberId){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", toDo='" + toDo + '\'' +
                ", detail='" + detail + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", jobList=" + jobList +
                ", jobWorkers=" + jobWorkers.size() +
                '}';
    }
}
