package com.toyproject.demo.repository.member;

import com.toyproject.demo.ServerUrl;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.dto.member.MemberModificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
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

    @Override
    public Long modificationMemberName(MemberModificationDto memberModificationDto) {
        Member findMember = em.find(Member.class, memberModificationDto.getId());
        log.info("modificationMemberName -> before Member Name : {}" , findMember.getName());
        findMember.setName(memberModificationDto.getName());
        log.info("modificationMemberName -> after Member Name : {}" , findMember.getName());
        return memberModificationDto.getId();
    }

    @Override
    public Long memberProfileUpdate(MultipartFile multipartFile, Long id) throws Exception {

//        String originalFilename = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString() + ".png";
        String url = ServerUrl.imageUrl;
        Path path = Paths.get(url + uuid);

        try{

            Files.write(path,multipartFile.getBytes());
            Member findMember = em.find(Member.class, id);
            findMember.setImageUuid(uuid);
        }
        catch (Exception e){
            System.out.println("Error");
        }
        return id;
    }

    @Override
    public String memberProfileGetUuid(Long id) {
        Member member = em.find(Member.class, id);
        String memberImageUuid = member.getImageUuid();

        if(memberImageUuid == null){
            return "";
        }
        return memberImageUuid;
    }


}
