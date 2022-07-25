package com.toyproject.demo.repository.sprint.diagram;

import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.sprint.Diagram;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemoryDiagramRepository implements DiagramRepository {

    private static HashMap<Long, Diagram> store = new HashMap<>();
    private static Long seq = 0L;

    @Override
    public Long save(Long sprintNum, Diagram diagram) {
        diagram.setId(++seq);
        diagram.setSprintNum(sprintNum);
        diagram.setLocalDateTime(LocalDateTime.now());
        store.put(seq, diagram);

        return seq;
    }

    @Override
    public String setSuperClass(String superClassName, Long SprintNum, Long diagramNum) {
        return null;
    }

    @Override
    public Diagram findByNum(Long diagramNum) {
        return store.get(diagramNum);
    }

    @Override
    public List<Diagram> findBySprintNum(Long sprintNum) {
        List<Diagram> diagramListBySprintNum = new ArrayList<>();

        Collection<Diagram> diagramList = store.values();

        for (Diagram diagram : diagramList) {
            if(diagram.getSprintNum() == sprintNum){
                diagramListBySprintNum.add(diagram);
            }
        }
        return diagramListBySprintNum;
    }

    @Override
    public List<Diagram> findAll() {
        List<Diagram> diagrams = new ArrayList<>(store.values());
        return diagrams;
    }
}
