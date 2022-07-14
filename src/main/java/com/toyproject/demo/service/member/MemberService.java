package com.toyproject.demo.service.member;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    public Message<Long> login(MemberDto memberDto);
    public Message<Long> save(Member member);
    public Message<String> checkAnswerFindPassword(MemberFindDto memberFindDto);
}
