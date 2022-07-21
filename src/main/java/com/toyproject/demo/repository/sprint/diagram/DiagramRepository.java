package com.toyproject.demo.repository.sprint.diagram;

import com.toyproject.demo.domain.sprint.Diagram;

public interface DiagramRepository {

    Long save(Long SprintNum, Diagram diagram);

    String setSuperClass(String superClassName, Long SprintNum, Long diagramNum);

    Diagram findByNum(Long diagramNum);
}
