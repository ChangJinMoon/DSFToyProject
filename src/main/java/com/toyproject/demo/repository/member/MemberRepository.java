package com.toyproject.demo.repository.member;

import com.toyproject.demo.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {

    Long save(Member member);

    Member findMember(Long id);

    List<Member> findAll();
}
