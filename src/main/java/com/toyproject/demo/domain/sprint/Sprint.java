package com.toyproject.demo.domain.sprint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprint_id")
    private Long id;

    private String sprintName;

    private String sprintDetail;

    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectDetail project;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id")
    private List<Code> code;

    public static Sprint createSprint(String sprintName, String sprintDetail, ProjectDetail projectDetail){
        Sprint sprint = new Sprint();
        sprint.sprintName = sprintName;
        sprint.sprintDetail = sprintDetail;
        sprint.project = projectDetail;
        sprint.localDateTime = LocalDateTime.now();
        return sprint;
    }

    public void updateSprint(String sprintName, String sprintDetail){
        this.sprintName = sprintName;
        this.sprintDetail = sprintDetail;
    }
}