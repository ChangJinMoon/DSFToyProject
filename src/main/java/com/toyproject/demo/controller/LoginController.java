package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;

import com.toyproject.demo.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;

    // 필요 없음
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody MemberDto memberDto) throws URISyntaxException {
        Message message = memberService.login(memberDto);
        log.info("ID: {}가 로그인 시도",memberDto.getEmail());

        if(message.getStatusEum() == StatusEnum.OK){
            URI redirectUri = new URI("http://localhost:8080/personalPage/" + message.getData());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
            return new ResponseEntity<Message>(message,httpHeaders,HttpStatus.FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    // 필요 없음
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public ResponseEntity<Message> save(@RequestBody Member member){
        Message<Long> message = memberService.save(member);
        log.info("Member join 실행 정상적으로 이뤄짐.");
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
}
