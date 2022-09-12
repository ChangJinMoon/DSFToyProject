package com.toyproject.demo.service.member;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import com.toyproject.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class MysqlMemberService implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Message<Long> login(MemberDto memberDto) {
        List<Member> memberList = memberRepository.findAll();
        Message<Long> message = new Message<>();

        if(!memberList.isEmpty()){
            for (Member member : memberList) {
                if (member.getEmail()!= null && member.getEmail().equals(memberDto.getEmail())){
                    if(member.getPassword() != null && member.getPassword().equals(memberDto.getPassword())){
                        message.setMessage("로그인 성공");
                        message.setData(member.getId());
                        message.setStatusEum(StatusEnum.OK);
                        return message;
                    }
                }
            }
        }
        message.setMessage("로그인 실패");
        message.setStatusEum(StatusEnum.NOT_FOUND);
        return message;
    }

    @Transactional
    @Override
    public Message<Long> save(Member member){
        Long memberId = memberRepository.save(member).get();
        if(memberId == -1L){
            Message<Long> message = new Message<>(StatusEnum.BAD_REQUEST_AUTHORIZATION);
            message.setMessage("중복아이디 존재");
            return message;
        }

        Message<Long> message = new Message<>(StatusEnum.OK);
        message.setMessage("회원가입 성공");
        message.setData(memberId);
        return message;
    }

    @Override
    public Message<String> checkAnswerFindPassword(MemberFindDto memberFindDto) {
        List<Member> memberList = memberRepository.findAll();
        Message<String> message = new Message<>();

        if (!memberList.isEmpty()){
            for (Member member : memberList) {
                if(member.getEmail().equals(memberFindDto.getEmail())
                        && member.getFindPasswordAnswer().equals(memberFindDto.getFindPasswordAnswer())){
                    message.setData(member.getPassword());
                    message.setStatusEum(StatusEnum.OK);
                    message.setMessage("비밀번호 찾기 성공");

                    return message;
                }
            }
        }

        message.setData("비밀번호 찾기 실패");
        message.setMessage("비밀번호 찾기 실패");
        message.setStatusEum(StatusEnum.OK);
        return message;
    }
}
