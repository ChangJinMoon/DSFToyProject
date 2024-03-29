package com.toyproject.demo.service.member;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.ServerUrl;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import com.toyproject.demo.dto.member.MemberInfoDto;
import com.toyproject.demo.dto.member.MemberModificationDto;
import com.toyproject.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
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

        log.info("회원이름 수정 : {}",modificationMemberId);
        return message;
    }

    @Transactional
    @Override
    public Message<Long> memberProfileUpdate(MultipartFile multipartFile, Long id) throws Exception {
        Long updateId = memberRepository.memberProfileUpdate(multipartFile, id);
        Message<Long> message = new Message<>(StatusEnum.OK);
        message.setMessage("이미지 저장");
        message.setData(updateId);
        return message;
    }

    @Override
    public ResponseEntity<Resource> memberProfileGet(Long id) throws Exception {
        String uuid  = memberRepository.memberProfileGetUuid(id);
        Message<FileSystemResource> message = new Message<>();
        if(uuid == null){
            message.setMessage("사진이 존재하지 않습니다.");
            message.setStatusEum(StatusEnum.OK);
        }

        try {
//            String memberProfileUrl = "/Users/yangbyoungseok/IdeaProjects/DSFToyProject/src/main/resources/image/" + uuid;
            String memberProfileUrl = ServerUrl.imageUrl + uuid;
            FileSystemResource resource = new FileSystemResource(memberProfileUrl);
            if (!resource.exists()) {
                throw new Exception();
            }
            HttpHeaders header = new HttpHeaders();
            Path filePath = null;
            filePath = Paths.get(memberProfileUrl);
            header.add("Content-Type", Files.probeContentType(filePath));
            message.setMessage("사진 가져오기");
            message.setData(resource);
            message.setStatusEum(StatusEnum.OK);
            return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);

        } catch (Exception e) {
            throw new Exception();
        }

    }
}
