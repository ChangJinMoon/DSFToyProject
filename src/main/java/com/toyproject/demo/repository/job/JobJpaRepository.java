package com.toyproject.demo.repository.job;

import com.toyproject.demo.domain.job.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JobJpaRepository implements JobRepository{

    private final EntityManager entityManager;

    @Override
    public void update(Job job) {
        entityManager.merge(job);
    }

    @Override
    public void delete(Job job) {
        entityManager.remove(job);
    }

    @Override
    public Optional<Job> findJobById(Long id) {
        return Optional.ofNullable(entityManager.find(Job.class,id));
    }
}
