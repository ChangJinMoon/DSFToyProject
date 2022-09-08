package com.toyproject.demo.domain;

import com.toyproject.demo.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String projectId;
    private String projectName;
    private String projectDetail;
    private int projectLeader;

    @ManyToMany // 임시
    @JoinColumn(name = "member_id")
    private List<Member> memberList;
}
