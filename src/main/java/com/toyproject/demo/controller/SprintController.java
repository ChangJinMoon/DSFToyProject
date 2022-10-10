package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.SessionKey;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.projectDetail.ProjectDetailDeleteRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailUpdateRequestDto;
import com.toyproject.demo.dto.sprint.SprintIdRequestDto;
import com.toyproject.demo.dto.sprint.SprintInitDto;
import com.toyproject.demo.repository.session.SessionImpl;
import com.toyproject.demo.service.sprint.SprintServiceImpl;
import lombok.RequiredArgsConstructor;
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

@Controller
@RequiredArgsConstructor

public class SprintController {

    private final SprintServiceImpl sprintService;
    private final SessionImpl session;
    SessionKey sessionKey = new SessionKey();

    @GetMapping("/Sprint/{sprintId}")
    public ResponseEntity<Message> init(@RequestBody SprintIdRequestDto sprintIdRequestDto){
        Message<SprintInitDto> response = sprintService.init(sprintIdRequestDto.getSprintId());
        HttpHeaders headers = makeJsonHttpHeaders();
        // save Sprint session
        session.save(sessionKey.makeSprintSessionKey(sprintIdRequestDto.getUserId()), sprintIdRequestDto.getSprintId());

        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(headers).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @DeleteMapping("/Sprint/{id}")
    public ResponseEntity<Message> deleteSprint(@PathVariable Long id,
                                                @RequestBody ProjectDetailDeleteRequestDto deleteRequestDto){
        Message<String> response = sprintService.deleteSprint(id,deleteRequestDto);
        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        if(response.getStatusEum() == StatusEnum.BAD_REQUEST_AUTHORIZATION)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/Sprint/update/{id}")
    public ResponseEntity<Message> updateSprint(@PathVariable Long id,
                                                @RequestBody ProjectDetailUpdateRequestDto updateRequestDto){
        Message<String> response = sprintService.updateSprint(id,updateRequestDto);
        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        if(response.getStatusEum() == StatusEnum.BAD_REQUEST_AUTHORIZATION)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/Sprint/now/{userId}")
    public ResponseEntity<Message> getOne(@PathVariable Long userId){
        Message<Long> response = new Message<>();
        response.setStatusEum(StatusEnum.OK);
        response.setData(session.getInfo(sessionKey.makeSprintSessionKey(userId)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public HttpHeaders redirectHome(HttpServletRequest request, Long param){
        HttpHeaders headers = makeJsonHttpHeaders();
        URI redirect = ServletUriComponentsBuilder.fromContextPath(request)
                .path("/personalProject/{projectId}")
                .buildAndExpand("projectId",param)
                .toUri();
        headers.setLocation(redirect);
        return headers;
    }

    private HttpHeaders makeJsonHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return headers;
    }
}
