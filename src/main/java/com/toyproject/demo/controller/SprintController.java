package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.service.sprint.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;


    //임시 폼 -> Sprint를 전달 함
    @GetMapping("/{id}/{projectNum}/{SprintNum}")
    public ResponseEntity<Message> home(
            @PathVariable Long id, @PathVariable Long projectNum , @PathVariable String SprintNum){

        Message<List<Sprint>> message = sprintService.getSprintByProject(projectNum);
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    @PostMapping("/{id}/{projectNum}/save")
    public ResponseEntity<Message> save(@PathVariable Long id, @PathVariable Long projectNum, @RequestBody Sprint sprint){
        System.out.println(sprint.toString());
        Message<Long> message = sprintService.save(projectNum,sprint);


        return ResponseEntity.status(HttpStatus.OK).body(message);
    }





}
