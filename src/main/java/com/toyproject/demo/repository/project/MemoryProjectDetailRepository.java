package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.Sprint;
import com.toyproject.demo.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryProjectDetailRepository implements ProjectDetailRepository{

    private static Long seq = 0L;
    private static Map<Long, Sprint> store = new HashMap<>();

    @Override
    public List<Sprint> find(Long projectNum) {
        List<Sprint> sprints = new ArrayList<>();

        for(Long id : store.keySet()){
            Sprint check = store.get(id);

            if (check.getProjectNum() == projectNum) {
                sprints.add(check);
            }
        }
        return sprints;
    }

    @Override
    public Long save(Long projectNum,Sprint sprint) {
        sprint.setId(++seq);
        store.put(seq, sprint);
        return seq;
    }

    @Override
    public Long deleteSprint(Long sprintId) {
        return null;
    }
}
