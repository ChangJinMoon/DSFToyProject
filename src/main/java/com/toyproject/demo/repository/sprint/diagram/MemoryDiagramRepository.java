package com.toyproject.demo.repository.sprint.diagram;

import com.toyproject.demo.domain.sprint.Diagram;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;

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
}
