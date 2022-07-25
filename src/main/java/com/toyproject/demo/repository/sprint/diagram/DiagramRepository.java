package com.toyproject.demo.repository.sprint.diagram;

import com.toyproject.demo.domain.sprint.Diagram;

import java.util.List;

public interface DiagramRepository {

    Long save(Long sprintNum, Diagram diagram);

    String setSuperClass(String superClassName, Long SprintNum, Long diagramNum);

    Diagram findByNum(Long diagramNum);

    List<Diagram> findBySprintNum(Long sprintNum);

    List<Diagram> findAll();
}
