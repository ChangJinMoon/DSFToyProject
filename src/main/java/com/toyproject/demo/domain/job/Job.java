package com.toyproject.demo.domain.job;

import com.toyproject.demo.domain.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO: 2022/11/19 create making Job Object method

@Entity
@Table(name = "job")
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toDo;

    private String detail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sprint_id")
    private JobList jobList;

    @OneToMany
    private List<Member> jobWorks = new ArrayList<>();

}
