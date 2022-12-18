package com.toyproject.demo.repository.member;

import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberModificationDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Long save(Member member);

    Optional<Member> findMember(Long id);

    List<Member> findAll();

    Optional<Member> findByEmail(String email);

    Long modificationMemberName(MemberModificationDto memberModificationDto);

    Long memberProfileUpdate(MultipartFile multipartFile, Long id) throws Exception;

    String memberProfileGetUuid(Long id);
}
