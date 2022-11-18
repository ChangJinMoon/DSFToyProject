package com.toyproject.demo.service.member;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import com.toyproject.demo.dto.member.MemberInfoDto;
import com.toyproject.demo.dto.member.MemberModificationDto;
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
    public Message<MemberInfoDto> login(MemberDto memberDto) {

        Optional<Member> findMember = memberRepository.findByEmail(memberDto.getEmail());
        Message<MemberInfoDto> message = new Message<>();

        if(findMember.isPresent()){
            Member member = findMember.get();
            if(member.getPassword().equals(memberDto.getPassword())){
                MemberInfoDto memberInfoDto = new MemberInfoDto();
                memberInfoDto.setEmail(member.getEmail());
                memberInfoDto.setName(member.getName());
                memberInfoDto.setId(member.getId());

                message.setMessage("로그인 성공");
                message.setData(memberInfoDto);
                message.setStatusEum(StatusEnum.OK);
                return message;
            }
        }
        message.setMessage("로그인 실패");
        message.setStatusEum(StatusEnum.NOT_FOUND);
        return message;
    }

    @Transactional
    @Override
    public Message<Long> save(Member member){
        Long memberId = memberRepository.save(member);
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
        Optional<Member> findMember = memberRepository.findByEmail(memberFindDto.getEmail());
        Message<String> message = new Message<>();

        if(findMember.isPresent()){
            Member member = findMember.get();
            if(member.getFindPasswordAnswer().equals(memberFindDto.getFindPasswordAnswer())){
                message.setData(member.getPassword());
                message.setStatusEum(StatusEnum.OK);
                message.setMessage("비밀번호 찾기 성공");
                return message;
            }
        }

        message.setData("비밀번호 찾기 실패");
        message.setMessage("비밀번호 찾기 실패");
        message.setStatusEum(StatusEnum.OK);
        return message;
    }

    @Override
    public Message<MemberInfoDto> findById(Long id) {
        Optional<Member> member = memberRepository.findMember(id);
        Message<MemberInfoDto> message = new Message<>();
        if(member.isPresent()){
            MemberInfoDto memberInfoDto = new MemberInfoDto();
            memberInfoDto.setEmail(member.get().getEmail());
            memberInfoDto.setName(member.get().getName());
            memberInfoDto.setId(member.get().getId());
            message.setMessage("멤버정보");
            message.setStatusEum(StatusEnum.OK);
            message.setData(memberInfoDto);
            return message;
        }

        message.setMessage("멤버정보");
        message.setStatusEum(StatusEnum.NOT_FOUND);
        message.setData(null);
        return message;


    }

    @Transactional
    @Override
    public Message<Long> modificationMember(MemberModificationDto memberModificationDto) {
        Long modificationMemberId = memberRepository.modificationMemberName(memberModificationDto);
        Message<Long> message = new Message<>(StatusEnum.OK);
        message.setData(modificationMemberId);
        message.setMessage("회원 이름 수정 완료");
        return message;
    }
}
