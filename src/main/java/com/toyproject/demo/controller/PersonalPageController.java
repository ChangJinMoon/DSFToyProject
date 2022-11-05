package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageInitDto;
import com.toyproject.demo.dto.personalpage.request.PersonalPageInitRequestDto;
import com.toyproject.demo.service.presonalproject.PersonalProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonalPageController {
    private final PersonalProjectServiceImpl personalProjectService;

    @GetMapping("/personalPage")
    public ResponseEntity<Message> init(HttpServletRequest request){
        Message<List<PersonalPageInitDto>> init = personalProjectService
                .init(Long.parseLong(request.getParameter("id")));

        HttpHeaders headers = makeJsonHttpHeaders();

        //Bad Authorization 구현 하기

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(init);
    }

    @PostMapping("/personalPage")
    public ResponseEntity<Message> addProject(HttpServletRequest request,
                                              @RequestBody PersonalPageAddRequestDto dto){
        Message<String> response = personalProjectService.addProject(dto.getUserId(),dto);
        HttpHeaders headers = new HttpHeaders();

        //redirect
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        URI redirect = ServletUriComponentsBuilder.fromContextPath(request)
                .path("/personalPage/{id}")
                .buildAndExpand("id",dto.getUserId())
                .toUri();
        headers.setLocation(redirect);

        if(response.getStatusEum() == StatusEnum.INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        else
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    private HttpHeaders makeJsonHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return headers;
    }
}
