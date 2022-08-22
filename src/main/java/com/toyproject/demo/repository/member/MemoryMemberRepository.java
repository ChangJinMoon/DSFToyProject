package com.toyproject.demo.repository.member;

import com.toyproject.demo.domain.member.Member;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Long seq = 0L;
    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public Optional<Long> save(Member member) {
        List<Member> members = new ArrayList<>(store.values());

        for (Member value : store.values()) {
            if(value.getEmail().equals(member.getEmail())){
                return Optional.of(-1L);
            }
        }

        member.setId(++seq);
        store.put(seq, member);
        //member 예외 처리 해야됨
        return Optional.of(seq);

    }

    @Override
    public Optional<Member> findMember(Long id) {
        Member findMember = store.get(id);
        return Optional.ofNullable(findMember);
    }

    @Override
    public Optional<List<Member>> findAll() {
        List<Member> members = new ArrayList<>(store.values());
        return Optional.ofNullable(members);
    }

    public Member findPassword(String id, String answer) {
        return null;
    }
}
