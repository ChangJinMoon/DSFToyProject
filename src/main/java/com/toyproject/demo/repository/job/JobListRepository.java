package com.toyproject.demo.repository.job;

import com.toyproject.demo.domain.job.JobList;

import java.util.Optional;

public interface JobListRepository {
    void save(JobList jobList);

    void update(JobList jobList);

    void delete(JobList jobList);

    Optional<JobList> findJobListById(Long id);
}
