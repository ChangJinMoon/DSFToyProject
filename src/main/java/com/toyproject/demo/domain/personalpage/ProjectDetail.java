package com.toyproject.demo.domain.personalpage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.sprint.Diagram;
import com.toyproject.demo.domain.sprint.Sprint;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
public class ProjectDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    private String projectName;

    private String projectDetail;

    private Long projectLeader;

    @JsonManagedReference
    @OneToMany(mappedBy = "project" , cascade = CascadeType.ALL)
    private List<MemberProject> members = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Sprint> sprintList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Diagram> diagrams = new ArrayList<>();


    public ProjectDetail(){

    }

    public void changeNameOrDetail(@Nullable String projectName, @Nullable String projectDetail){
        if(projectName != null)
            this.projectName = projectName;
        if(projectDetail != null)
            this.projectDetail = projectDetail;
    }

    public void addMember(Member member){
        members.add(new MemberProject().createMemberProject(member));
    }

    public ProjectDetail createProject(String projectName, String projectDetail, Long projectLeader, MemberProject memberProject){
        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.projectName = projectName;
        projectDetail1.projectDetail = projectDetail;
        projectDetail1.projectLeader = projectLeader;
        projectDetail1.members.add(memberProject);
        projectDetail1.getMembers().get(projectDetail1.members.size()-1).setProject(projectDetail1);
        return projectDetail1;
    }

    public void setProjectDetail(String update){
        this.projectDetail = update;
    }

}
