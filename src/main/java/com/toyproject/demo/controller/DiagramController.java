package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Diagram;
import com.toyproject.demo.service.sprint.diagram.DiagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DiagramController {

    private final DiagramService diagramService;

    @PostMapping("/{id}/{projectNum}/{sprintNum}/save")
    public ResponseEntity<Message> save (@PathVariable Long sprintNum, @RequestBody Diagram diagram){
        Message<Long> message = diagramService.save(sprintNum, diagram);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


}
