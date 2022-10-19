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

    // Optional을 사용하는게 맞는지 고민
    @Override
    public Long save(Member member) {
        Optional<Member> check = findByEmail(member.getEmail());
        if(check.isPresent()){
            return -1L;
        }

        em.persist(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findMember(Long id) {
        Member member = em.find(Member.class, id);
        Optional<Member> findMember = Optional.of(member);
        return findMember;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        return members;
    }

    @Override
    public Optional<Member> findByEmail(String email) {

        List<Member> findMember = em.createQuery("select m from Member m where m.email = :email", Member.class).setParameter("email", email)
                .getResultList();
        if(findMember.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(findMember.get(0));
    }
}
