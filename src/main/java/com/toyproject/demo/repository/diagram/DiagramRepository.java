package com.toyproject.demo.repository.diagram;

import com.toyproject.demo.domain.sprint.Diagram;

import java.util.List;
import java.util.Optional;

public interface DiagramRepository {

    Long save(Long projectId, Diagram diagram);

    String setSuperClass(String superClassName, Long SprintNum, Long diagramNum);

    Optional<Diagram> findByNum(Long diagramNum);

    List<Diagram> findByProjectNum(Long projectNum);

    List<Diagram> findAll();
}
