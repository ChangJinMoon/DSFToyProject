package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.Project;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.service.presonalproject.PersonalProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonalPageController {
    private final PersonalProjectService personalProjectService;

    @GetMapping("/personalPage/{id}")
    public ResponseEntity<Message> init(@PathVariable int id){
        Message<List<Project>> init = personalProjectService.init(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        //Bad Authorization 구현 하기
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(init);
    }

    @PostMapping("/personalPage/{id}")
    public ResponseEntity<Message> addProject(@PathVariable int id,
                                              PersonalPageAddRequestDto dto){
        Message<List<Project>> response = personalProjectService.addProject(id,dto.getProject());
        if(response.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/personalPage/{id}/{projectId}")
    public ResponseEntity<Message> deleteProject(@PathVariable int id, @PathVariable String projectId){
        Message<List<Project>> response = personalProjectService.deleteProject(projectId);
        if(response.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/personalPage/update/{id}/{projectId}")
    public ResponseEntity<Message> updateProject(@PathVariable int id, @PathVariable String projectId,
                                                     PersonalPageUpdateRequestDto dto){
        Message<List<Project>> message = personalProjectService.updateProject(projectId, dto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        if(message.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(headers).body(message);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(message);
    }
}
