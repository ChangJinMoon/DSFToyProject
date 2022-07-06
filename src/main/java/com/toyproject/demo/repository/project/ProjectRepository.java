package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    boolean save(Project project);
    //Project findProjectById(int userId);
    Optional<List<Project>> findAllProject(int userId);
    void delete(String projectId);
    void update(Project project);
    int getProjectSize();
    Optional<Project> findProject(String projectId);
}
