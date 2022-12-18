package com.toyproject.demo.repository.member;

import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberModificationDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Long seq = 0L;
    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public Long save(Member member) {
        List<Member> members = new ArrayList<>(store.values());

        for (Member value : store.values()) {
            if(value.getEmail().equals(member.getEmail())){
                return -1L;
            }
        }

        member.setId(++seq);
        store.put(seq, member);
        //member 예외 처리 해야됨
        return seq;

    }

    @Override
    public Optional<Member> findMember(Long id) {
        Member findMember = store.get(id);
        return Optional.ofNullable(findMember);
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>(store.values());
        return members;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Long modificationMemberName(MemberModificationDto memberModificationDto) {
        return null;
    }

    @Override
    public Long memberProfileUpdate(MultipartFile multipartFile, Long id) {
        return null;
    }

    @Override
    public String memberProfileGetUuid(Long id) {
        return null;
    }

    public Member findPassword(String id, String answer) {
        return null;
    }
}
