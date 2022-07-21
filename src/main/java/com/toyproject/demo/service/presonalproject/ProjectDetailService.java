package com.toyproject.demo.service.presonalproject;

import com.toyproject.demo.domain.sprint.Sprint;

import java.util.List;

public interface ProjectDetailService {

    List<Sprint> find(Long projectNum);
    Long save(Long projectNum, Sprint sprint);
    Long delete(Long sprintId);
    List<Sprint> findById(Long sprintId);
    List<Sprint> findByName(String name);
}
