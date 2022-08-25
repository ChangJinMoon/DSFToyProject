package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Diagram;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.repository.diagram.DiagramRepository;
import com.toyproject.demo.service.sprint.SprintService;
import com.toyproject.demo.service.diagram.DiagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiagramController {

    private final DiagramService diagramService;
    private final DiagramRepository diagramRepository;
    private final SprintService sprintService;

    @PostMapping("/{id}/{projectNum}/{sprintNum}/save")
    public ResponseEntity<Message> save (@PathVariable Long projectNum, @RequestBody Diagram diagram){
        Message<Long> message = diagramService.save(projectNum, diagram);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/{id}/{projectNum}/diagram")
    public ResponseEntity<Message> makeDiagram(@PathVariable Long id
            , @PathVariable Long projectNum){
        Message<List<MultiValueMap>> message = diagramService.makeDiagrams(diagramRepository.findByProjectNum(projectNum),projectNum);
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }


    /**
     *
     * @param projectNum
     * 좌측에 프로젝트 숫자에 해당하는 스프린트 반환.
     * @return ResponseEntity(Message) -> All sprint according to the number of projects
     */

    @GetMapping("{id}/{projectNum}/allSprintByProjectNum")
    public ResponseEntity<Message> allSpringByProjectNum(@PathVariable Long projectNum){
        Message<List<Sprint>> message = sprintService.getSprintByProject(projectNum);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


}
