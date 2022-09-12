package com.toyproject.demo.service.diagram;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Diagram;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface DiagramService {
    Message<Long> save(Long projectNum, Diagram diagram);
    Message<Diagram> findById(Long diagramNum);
    Message<List<MultiValueMap>>makeDiagrams(List<Diagram> diagramListBySprintNum, Long projectNum);

}
