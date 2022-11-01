package com.toyproject.demo.service.member;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import com.toyproject.demo.dto.member.MemberInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Message<MemberInfoDto> login(MemberDto memberDto);
    Message<Long> save(Member member);
    Message<String> checkAnswerFindPassword(MemberFindDto memberFindDto);
    Message<MemberInfoDto> findById(Long id);
}
