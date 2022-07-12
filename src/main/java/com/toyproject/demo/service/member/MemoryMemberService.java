package com.toyproject.demo.service.member;

import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import com.toyproject.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoryMemberService implements MemberService{

    private final MemberRepository memberRepository;

    public Long login(MemberDto memberDto){
        List<Member> memberList = memberRepository.findAll();
        Optional<Long> memberId;
        for (Member member : memberList) {
            if (member.getEmail().equals(memberDto.getEmail())){
                if(member.getPassword().equals(memberDto.getPassword())){
                    return member.getId();
                    //memberId = Optional.ofNullable(member.getId());
                }
            }
        }
        return -1L;
    }

    public Long save(Member member){
        Long memberId = memberRepository.save(member);
        return memberId;
    }

    public String checkAnswerFindPassword(MemberFindDto memberFindDto){
        List<Member> memberList = memberRepository.findAll();
        for (Member member : memberList) {
            if(member.getEmail().equals(memberFindDto.getEmail())){
                if(member.getFindPasswordAnswer().equals(memberFindDto.getFindPasswordAnswer())){
                    return member.getPassword();
                }
            }
        }
        return null;
    }
}
