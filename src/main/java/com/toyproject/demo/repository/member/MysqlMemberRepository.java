package com.toyproject.demo.repository.member;

import com.toyproject.demo.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MysqlMemberRepository implements MemberRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Long> save(Member member) {
        em.persist(member);
        Optional<Long> newID = Optional.of(member.getId());
        return newID;
    }

    @Override
    public Optional<Member> findMember(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Member>> findAll() {
        return Optional.empty();
    }
}
