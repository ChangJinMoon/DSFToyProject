package com.toyproject.demo.domain.job;

import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: 2022/11/19 make add jobList method
@Entity
@Getter
@Table(name = "job_list")
public class JobList extends Sprint {

    @OneToMany(mappedBy = "jobList" , cascade = CascadeType.ALL)
    private List<Job> jobList = new ArrayList<>();

    @Override
    public JobList createSprint(String sprintName, String sprintDetail, ProjectDetail projectDetail) {
        JobList jobList = new JobList();
        jobList.sprintName = sprintName;
        jobList.sprintDetail = sprintDetail;
        jobList.localDateTime = LocalDateTime.now();
        jobList.project = projectDetail;
        return jobList;
    }

    @Override
    public void updateSprint(String sprintName, String sprintDetail){
        super.sprintName = sprintName;
        super.sprintDetail = sprintDetail;
    }


}
