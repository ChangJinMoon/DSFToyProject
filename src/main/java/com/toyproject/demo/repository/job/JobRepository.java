package com.toyproject.demo.repository.job;

import com.toyproject.demo.domain.job.Job;

import java.util.Optional;

public interface JobRepository {
    void update(Job job);
    void delete(Job job);
    Optional<Job> findJobById(Long id);
}
