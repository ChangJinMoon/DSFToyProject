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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<Message> login(MemberDto memberDto){
        Long id = memberService.login(memberDto);
        Message<Long> message = new Message();

        if(id == -1L){
            message.setMessage("로그인 실패");
            log.info("login -> 로그인 실패");
        }
        else{
            message.setData(id);
            message.setMessage("로그인 성공");
            message.setStatusEum(StatusEnum.OK);
            log.info("login -> id: {} 로그인",id);

        }
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public ResponseEntity<Message> save(Member member){
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
    public ResponseEntity<Message> checkAnswerFindPassword(MemberFindDto memberFindDto){
        String password = memberService.checkAnswerFindPassword(memberFindDto);
        Message<String> message = new Message<>();
        if(password == null){
            message.setMessage("답변이 틀림");
            log.info("checkAnswerFindPassword -> 실패 ");
        }
        else{
            message.setStatusEum(StatusEnum.OK);
            message.setMessage("답변이 맞음, 비밀번호를 보여줌");
            message.setData(password);
            log.info("checkAnswerFindPassword -> 성공 email : {}",memberFindDto.getEmail());
        }

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
