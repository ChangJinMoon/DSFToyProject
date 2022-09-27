package com.toyproject.demo.controller;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberJoinDto;
import com.toyproject.demo.service.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginControllerTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Rollback
    void joinTest(){
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setName("userA");
        memberJoinDto.setEmail("userA@naver.com");
        memberJoinDto.setPassword("userPassword123!");
        memberJoinDto.setFindPasswordAnswer("findAnswer");

        Member member = memberJoinDto.DtoToEntity(memberJoinDto);

        Message<Long> save = memberService.save(member);

        Assertions.assertThat(save.getStatusEum()).isEqualTo(StatusEnum.OK);

    }


}