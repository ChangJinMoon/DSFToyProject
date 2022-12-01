package com.toyproject.demo.repository.job;

import com.toyproject.demo.domain.job.JobList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JobListJpaRepository implements JobListRepository{

    private final EntityManager entityManager;

    @Override
    public void save(JobList jobList) {
        entityManager.persist(jobList);
    }

    @Override
    public void update(JobList jobList) {
        entityManager.merge(jobList);
    }

    @Override
    public void delete(JobList jobList) {
        entityManager.remove(jobList);
    }

    @Override
    public Optional<JobList> findJobListById(Long id) {
        return Optional.ofNullable(entityManager.find(JobList.class, id));
    }
}
