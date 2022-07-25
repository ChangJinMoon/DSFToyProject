package com.toyproject.demo.service.sprint;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Sprint;

import java.util.List;

public interface SprintService {

    Message<Long> save(Long projectNum, Sprint sprint);
    Message<List<Sprint>> getSprintByProject(Long projectNum);

}
