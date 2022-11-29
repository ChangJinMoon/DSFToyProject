package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailAddRequestDto;
import com.toyproject.demo.dto.projectDetail.request.PersonalProjectDeleteRequestDto;
import com.toyproject.demo.dto.projectDetail.request.PersonalProjectGetOneRequestDto;
import com.toyproject.demo.dto.projectDetail.request.PersonalProjectInitRequestDto;
import com.toyproject.demo.dto.projectDetail.response.PersonalProjectGetOneResponseDto;
import com.toyproject.demo.dto.sprint.SprintInitDto;
import com.toyproject.demo.header.RestApiHeader;
import com.toyproject.demo.service.projectDetail.ProjectDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PersonalProjectController {

    private final ProjectDetailServiceImpl personalProjectService;

    @DeleteMapping("/personalProject")
    public ResponseEntity<Message> deleteProject(HttpServletRequest request
                                                    ,@RequestBody PersonalProjectDeleteRequestDto dto){
        Message<String> response = personalProjectService.deleteProject(dto.getProjectId());

        //redirect
        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        if(response.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        else
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(response);
    }

    @PutMapping("/personalProject/update")
    public ResponseEntity<Message> updateProject(HttpServletRequest request
                                                 ,@RequestBody PersonalPageUpdateRequestDto dto){
        Message<String> message = personalProjectService.updateProject(dto.getProjectId(),dto);
        //redirect
        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        if(message.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(message);
    }

    // sprint init
    @GetMapping("/personalProject")
    public ResponseEntity<Message> sprintInit(HttpServletRequest request){
        Message<List<SprintInitDto>> response =  personalProjectService
                .init(Long.parseLong(request.getParameter(request.getParameter("projectId"))));

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    // sprint add
    @PostMapping("/personalProject/addSprint")
    public ResponseEntity<Message> addSprint(HttpServletRequest request,
                                             @RequestBody ProjectDetailAddRequestDto dto){
        Message<String> response = personalProjectService.addSprint(dto.getProjectId(),dto);
        //redirect
        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(response);
    }

    @GetMapping("/personalProject/getOne")
    public ResponseEntity<Message> getOne(HttpServletRequest request){
        Message<PersonalProjectGetOneResponseDto> response = personalProjectService
                .getOne(Long.parseLong(request.getParameter("projectId")));

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}
