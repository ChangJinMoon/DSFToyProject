package com.toyproject.demo.domain;

import com.toyproject.demo.domain.job.Job;
import com.toyproject.demo.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "member_job")
@Getter
public class MemberJob {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_job_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member worker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_detail_id")
    private Job jobToDo;

    public static MemberJob createMemberJob(Member member){
        MemberJob temp = new MemberJob();
        temp.worker = member;
        return temp;
    }

    public static MemberJob createMemberJob(Job jobToDo){
        MemberJob temp = new MemberJob();
        temp.jobToDo = jobToDo;
        return temp;
    }

    public static MemberJob createMemberJob(Member member, Job jobToDo){
        MemberJob temp = new MemberJob();
        temp.worker = member;
        temp.jobToDo = jobToDo;
        return temp;
    }

}
