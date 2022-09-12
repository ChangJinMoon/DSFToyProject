package com.toyproject.demo.domain.sprint;

import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Sprint {

    @Id @GeneratedValue
    @Column(name = "sprint_id")
    private Long id;

    private String sprintName;

    private String sprintDetail;

    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectDetail project;

    public Sprint createSprint(String sprintName, String sprintDetail,ProjectDetail projectDetail){
        Sprint sprint = new Sprint();
        this.sprintName = sprintName;
        this.sprintDetail = sprintDetail;
        this.project = projectDetail;
        localDateTime = LocalDateTime.now();
        return sprint;
    }
}
