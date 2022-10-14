package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.SessionKey;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailAddRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectIdRequestDto;
import com.toyproject.demo.dto.sprint.SprintInitDto;
import com.toyproject.demo.repository.session.SessionImpl;
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

@Controller
@Slf4j
@RequiredArgsConstructor
public class PersonalProjectController {
    private final String personalPageHome = "/LandPage/{userId}";
    private final String projectPageHome = "/PersonalProject/{projectId}";
    private final ProjectDetailServiceImpl personalProjectService;

    @DeleteMapping("/personalProject/{projectId}")
    public ResponseEntity<Message> deleteProject(HttpServletRequest request
                                                    , @PathVariable Long projectId){
        Message<String> response = personalProjectService.deleteProject(projectId);

        //redirect
        HttpHeaders headers = redirectHome(request,projectId,1);

        if(response.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        else
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(response);
    }

    @PutMapping("/personalProject/update/{projectId}")
    public ResponseEntity<Message> updateProject(HttpServletRequest request
                                                 ,@PathVariable Long projectId, @RequestBody PersonalPageUpdateRequestDto dto){
        Message<ProjectDetail> message = personalProjectService.updateProject(projectId, dto);
        //redirect
        HttpHeaders headers = redirectHome(request,projectId,2);

        if(message.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(headers).body(message);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(message);
    }

    // sprint init
    @GetMapping("/personalProject/{projectId}")
    public ResponseEntity<Message> sprintInit(@PathVariable Long projectId){
        Message<List<SprintInitDto>> response =  personalProjectService.init(projectId);
        HttpHeaders headers = makeJsonHttpHeaders();

        if(response.getStatusEum() == StatusEnum.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    // sprint add
    @PostMapping("/personalProject/addSprint/{projectId}")
    public ResponseEntity<Message> addSprint(HttpServletRequest request,@PathVariable Long projectId,
                                             @RequestBody ProjectDetailAddRequestDto addRequestDto){
        Message<String> response = personalProjectService.addSprint(projectId,addRequestDto);
        //redirect
        HttpHeaders headers = redirectHome(request,projectId,2);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(response);
    }

    public HttpHeaders redirectHome(HttpServletRequest request,Long param,int located){
        HttpHeaders headers = makeJsonHttpHeaders();
        URI redirect = ServletUriComponentsBuilder.fromContextPath(request)
                .path((located == 1 ? personalPageHome : projectPageHome))
                .buildAndExpand(param)
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
