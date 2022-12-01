package com.toyproject.demo.repository.memberJob;

import com.toyproject.demo.domain.MemberJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberJobJpaRepository implements MemberJobRepository {

    private final EntityManager entityManager;

    @Override
    public boolean deleteMemberJob(MemberJob memberJob) {
        entityManager.remove(memberJob);
        return false;
    }
}
