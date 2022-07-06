package com.toyproject.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Project {
    private String projectId;
    private String projectName;
    private String projectDetail;
    private int projectLeader;
    private List<Integer> memberList;
}
