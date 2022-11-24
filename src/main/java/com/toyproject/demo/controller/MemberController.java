package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.*;

import com.toyproject.demo.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 필요 없음
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody MemberDto memberDto){
        Message message = memberService.login(memberDto);
        log.info("ID: {}가 로그인 시도",memberDto.getEmail());
        log.info("input Id",memberDto.getEmail());
        log.info("input Id",memberDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // 필요 없음
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public ResponseEntity<Message> save(@RequestBody MemberJoinDto memberJoinDto){
        Member member = memberJoinDto.DtoToEntity(memberJoinDto);

        Message<Long> message = memberService.save(member);

        log.info("Member join 실행 정상적으로 이뤄짐.");
        log.info("email",member.getEmail());
        log.info("password",member.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/find-password")
    public String findPassword(){
        return "find-password";
    }

    @PostMapping("/find-password")
    public ResponseEntity<Message> checkAnswerFindPassword(@RequestBody MemberFindDto memberFindDto){
        Message message = memberService.checkAnswerFindPassword(memberFindDto);
        log.info("checkAnswerFindPassword execute");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/member")
    public ResponseEntity<Message> memberInfo(@RequestParam Long id){
        Message<MemberInfoDto> message = memberService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/member-modification")
    public ResponseEntity<Message> memberModification(@RequestBody MemberModificationDto memberModificationDto){
        Message<Long> message = memberService.modificationMember(memberModificationDto);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
