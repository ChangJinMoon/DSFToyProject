package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.dto.projectDetail.ProjectDetailDeleteRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailUpdateRequestDto;
import com.toyproject.demo.dto.sprint.SprintInitDto;
import com.toyproject.demo.header.RestApiHeader;
import com.toyproject.demo.service.sprint.SprintServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor

public class SprintController {
    private final SprintServiceImpl sprintService;

    @GetMapping("/Sprint")
    public ResponseEntity<Message> init(HttpServletRequest request){
        Message<SprintInitDto> response = sprintService
                .init(Long.parseLong(request.getParameter("sprintId")));

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(headers).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @DeleteMapping("/Sprint")
    public ResponseEntity<Message> deleteSprint(@RequestBody ProjectDetailDeleteRequestDto deleteRequestDto){
        Message<String> response = sprintService.deleteSprint(deleteRequestDto.getUserId(),deleteRequestDto);

        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        if(response.getStatusEum() == StatusEnum.BAD_REQUEST_AUTHORIZATION)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(response);
    }

    @PutMapping("/Sprint/update")
    public ResponseEntity<Message> updateSprint(@RequestBody ProjectDetailUpdateRequestDto dto){
        Message<String> response = sprintService.updateSprint(dto.getUserId(),dto);

        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        if(response.getStatusEum() == StatusEnum.BAD_REQUEST_AUTHORIZATION)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(response);
    }
}
