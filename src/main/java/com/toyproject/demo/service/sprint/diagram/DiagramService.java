package com.toyproject.demo.service.sprint.diagram;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Diagram;

public interface DiagramService {
    Message<Long> save(Long sprintNum, Diagram diagram);
    Message<Diagram> findById(Long diagramNum);
}
