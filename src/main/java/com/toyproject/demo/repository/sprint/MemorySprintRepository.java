package com.toyproject.demo.repository.sprint;

import com.toyproject.demo.domain.sprint.Sprint;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemorySprintRepository implements SprintRepository {

    private static Map<Long,Sprint> store = new HashMap<>();
    private static Long seq = 0L;

    @Override
    public Long save(Long projectNum, Sprint sprint) {
        sprint.setId(++seq);
        sprint.setProjectNum(projectNum);
        sprint.setLocalDateTime(LocalDateTime.now());
        store.put(seq,sprint);
        System.out.println(sprint.toString());
        return seq;
    }

    @Override
    public List<Sprint> allSprintByProject(Long projectNum) {
        List<Sprint> projectSprint = new ArrayList<>();

        for (Sprint value : store.values()) {
            if(value.getProjectNum() == projectNum){
                projectSprint.add(value);
            }

        }
        return projectSprint;
    }
}
