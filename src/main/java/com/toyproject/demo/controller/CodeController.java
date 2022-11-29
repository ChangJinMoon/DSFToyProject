package com.toyproject.demo.controller;


import com.toyproject.demo.Message;
import com.toyproject.demo.dto.code.request.DeleteCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.SaveCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.UpdateCodeBlockRequestDto;
import com.toyproject.demo.dto.code.response.CodeInitResponseDto;
import com.toyproject.demo.dto.code.response.DeleteCodeBlockResponseDto;
import com.toyproject.demo.dto.code.response.SaveCodeBlockResponseDto;
import com.toyproject.demo.dto.code.response.UpdateCodeBlockResponseDto;
import com.toyproject.demo.header.RestApiHeader;
import com.toyproject.demo.service.code.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;


    @GetMapping("project/sprint/code")
    public ResponseEntity<Message> init(HttpServletRequest request){
        Message<CodeInitResponseDto> result = codeService.init(
                Long.parseLong(request.getParameter("sprintId")));

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @PostMapping("project/sprint/code")
    public ResponseEntity<Message> saveCodeBlock(@RequestBody SaveCodeBlockRequestDto dto){
        Message<SaveCodeBlockResponseDto> result = codeService.saveCodeBlock(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @PutMapping("project/sprint/code")
    public ResponseEntity<Message> updateCodeBlock(@RequestBody UpdateCodeBlockRequestDto dto){
        Message<UpdateCodeBlockResponseDto> result = codeService.updateCodeBlock(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }

    @DeleteMapping("project/sprint/code")
    public ResponseEntity<Message> deleteCodeBlock(@RequestBody DeleteCodeBlockRequestDto dto){
        Message<DeleteCodeBlockResponseDto> result = codeService.deleteCodeBlock(dto);

        HttpHeaders headers = RestApiHeader.makeJsonHeader();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }
}
