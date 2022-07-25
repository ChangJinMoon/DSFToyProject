package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Diagram;
import com.toyproject.demo.repository.sprint.diagram.DiagramRepository;
import com.toyproject.demo.service.sprint.diagram.DiagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiagramController {

    private final DiagramService diagramService;
    private final DiagramRepository diagramRepository;

    @PostMapping("/{id}/{projectNum}/{sprintNum}/save")
    public ResponseEntity<Message> save (@PathVariable Long sprintNum, @RequestBody Diagram diagram){
        Message<Long> message = diagramService.save(sprintNum, diagram);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/{id}/{projectNum}/diagram/{sprintNum}")
    public ResponseEntity<Message> makeDiagram(@PathVariable Long id
            , @PathVariable Long projectNum, @PathVariable Long sprintNum){
        Message<List<MultiValueMap>> message = diagramService.makeDiagrams(diagramRepository.findBySprintNum(sprintNum),sprintNum);
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }


}
