package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.personalpage.ProjectDetail;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    //for preview
    //origin project
    boolean save(ProjectDetail project);
    Optional<ProjectDetail> findProject(Long projectId);
    Optional<List<ProjectDetail>> findAllProject(Long userId);
    void update(ProjectDetail project);
    boolean delete(Long projectId);
}
