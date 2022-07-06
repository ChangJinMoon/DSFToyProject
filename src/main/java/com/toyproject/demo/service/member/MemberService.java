package com.toyproject.demo.service.member;

import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    public Long login(MemberDto memberDto);
    public Long save(Member member);
    public String checkAnswerFindPassword(MemberFindDto memberFindDto);
}
