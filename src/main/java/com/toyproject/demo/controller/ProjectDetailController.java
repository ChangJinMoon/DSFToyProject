package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.Sprint;
import com.toyproject.demo.service.presonalproject.ProjectDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
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
            log.info("그룹에 속해있는 스프린트가 없음");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

        message.setMessage("스프린트를 가져옴");
        message.setStatusEum(StatusEnum.OK);
        message.setData(sprints);
        log.info("스프린트 조회 성공 group num = {} ",projectNum);

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
        log.info("스프린트 저장 성공 : id = {}",saveId);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/{memberId}/{projectNum}")
    public ResponseEntity<Message> deleteSprint(
            @PathVariable("memberId") Long MemberId, @PathVariable("projectNum") Long projectNum,
            Long sprintId){
        Message<Long> message = new Message<>();
        Long deleteId = projectDetailService.delete(sprintId);

        if(deleteId != null){
            message.setMessage("삭제 성공");
            message.setData(deleteId);
            message.setStatusEum(StatusEnum.OK);
            log.info("스프린트 삭제 성공 : id = {}",sprintId);
        }
        else{
            message.setStatusEum(StatusEnum.NOT_FOUND);
            message.setMessage("sprintId가 잘못됨");
            log.info("스프린트 삭제 실패 : id = {}",sprintId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    //sprint 검색



}
