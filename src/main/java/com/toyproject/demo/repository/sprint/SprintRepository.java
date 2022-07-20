package com.toyproject.demo.repository.sprint;

import com.toyproject.demo.domain.Sprint;

import java.util.List;

public interface SprintRepository {


    Long save(Long projectNum, Sprint sprint);
    List<Sprint> allSprintByProject(Long projectNum);
}
