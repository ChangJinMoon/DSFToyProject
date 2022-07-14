package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;

import com.toyproject.demo.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/home")
    public ResponseEntity<Message> login(@RequestBody MemberDto memberDto){
        Message message = memberService.login(memberDto);
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public ResponseEntity<Message> save(@RequestBody Member member){
        System.out.println("member = " + member.toString());
        memberService.save(member);
        Long id = member.getId();
        Message<Long> message = new Message<>();

        if(id > 0){
            message.setMessage("회원가입 성공");
            message.setData(id);
            message.setStatusEum(StatusEnum.OK);
            log.info("Member join -> id {} name: {}, email: {} ",id, member.getName(), member.getEmail());
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/find-password")
    public String findPassword(){
        return "find-password";
    }

    @PostMapping("/find-password")
    public ResponseEntity<Message> checkAnswerFindPassword(@RequestBody MemberFindDto memberFindDto){
        Message message = memberService.checkAnswerFindPassword(memberFindDto);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
