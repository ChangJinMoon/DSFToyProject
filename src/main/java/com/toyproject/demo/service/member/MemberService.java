package com.toyproject.demo.service.member;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberDto;
import com.toyproject.demo.dto.member.MemberFindDto;
import com.toyproject.demo.dto.member.MemberInfoDto;
import com.toyproject.demo.dto.member.MemberModificationDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MemberService {
    Message<MemberInfoDto> login(MemberDto memberDto);
    Message<Long> save(Member member);
    Message<String> checkAnswerFindPassword(MemberFindDto memberFindDto);
    Message<MemberInfoDto> findById(Long id);

    Message<Long> modificationMember(MemberModificationDto memberModificationDto);
    Message<Long> memberProfileUpdate(MultipartFile multipartFile,Long id) throws Exception;
    ResponseEntity<Resource> memberProfileGet(Long id) throws Exception;
}
