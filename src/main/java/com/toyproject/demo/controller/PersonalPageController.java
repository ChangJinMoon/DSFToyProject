package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.service.presonalproject.PersonalProjectService;
import com.toyproject.demo.service.presonalproject.PersonalProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonalPageController {
    private final PersonalProjectServiceImpl personalProjectService;

    @GetMapping("/personalPage/{id}")
    public ResponseEntity<Message> init(@RequestParam Long id){
        Message<List<ProjectDetail>> init = personalProjectService.init(id);
        HttpHeaders headers = makeJsonHttpHeaders();

        //Bad Authorization 구현 하기
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(init);
    }

    @PostMapping("/personalPage/{id}")
    public ResponseEntity<Message> addProject(HttpServletRequest request, @RequestParam Long id,
                                              @RequestBody PersonalPageAddRequestDto dto){
        Message<ProjectDetail> response = personalProjectService.addProject(id,dto);

        HttpHeaders headers = new HttpHeaders();

        //redirect
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        URI redirect = ServletUriComponentsBuilder.fromContextPath(request)
                .path("/personalPage/{id}")
                .buildAndExpand("id",id)
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
