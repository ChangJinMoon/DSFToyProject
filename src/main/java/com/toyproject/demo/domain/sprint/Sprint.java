package com.toyproject.demo.domain.sprint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.job.JobList;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn
@Getter
public abstract class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "sprint_id")
    private Long id;

    protected String sprintName;

    protected String sprintDetail;

    protected LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    protected ProjectDetail project;

    public abstract Sprint createSprint(String sprintName, String sprintDetail, ProjectDetail projectDetail);

    public abstract void updateSprint(String sprintName, String sprintDetail);
}