package com.toyproject.demo.repository.member;

import com.toyproject.demo.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Optional<Long> save(Member member);

    Optional<Member> findMember(Long id);

    Optional<List<Member>> findAll();
}
