package com.toyproject.demo.repository.member;

import com.toyproject.demo.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Long seq = 0L;
    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public Long save(Member member) {
        member.setId(++seq);
        store.put(seq, member);
        return seq;

    }

    @Override
    public Member findMember(Long id) {
        Member findMember = store.get(id);
        return findMember;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>(store.values());
        return members;
    }

    public Member findPassword(String id, String answer) {
        return null;
    }
}
