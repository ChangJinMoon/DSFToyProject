package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.Sprint;
import com.toyproject.demo.service.presonalproject.ProjectDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectDetailController {

    private final ProjectDetailService projectDetailService;

    @GetMapping("/{memberId}/{projectNum}")
    public ResponseEntity<Message> showSprint(
            @PathVariable("memberId") Long MemberId, @PathVariable("projectNum") Long projectNum){
        Message<List<Sprint>> message = new Message();

        //projectNum에 속해있는 스프린트를 가져온다.
        List<Sprint> sprints = projectDetailService.find(projectNum);

        if(sprints.isEmpty()){
            message.setMessage("프로젝트에 해당하는 스프린트가 존재하지 않음");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

        message.setMessage("스프린트를 가져옴");
        message.setStatusEum(StatusEnum.OK);
        message.setData(sprints);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/{memberId}/{projectNum}")
    public ResponseEntity<Message> saveSprint(
            @PathVariable("memberId") Long MemberId, @PathVariable("projectNum") Long projectNum,
            Sprint sprint){

        Message<Long> message = new Message();

        Long saveId = projectDetailService.save(projectNum, sprint);

        message.setMessage("스프린트 저장");
        message.setStatusEum(StatusEnum.OK);
        message.setData(saveId);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }



}
