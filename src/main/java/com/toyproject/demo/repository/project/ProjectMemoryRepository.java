package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.personalpage.ProjectDetail;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectMemoryRepository implements ProjectRepository{
    @Override
    public boolean save(ProjectDetail project) {
        return false;
    }

    @Override
    public Optional<ProjectDetail> findProject(Long projectId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ProjectDetail>> findAllProject(Long userId) {
        return Optional.empty();
    }

    @Override
    public void update(ProjectDetail project) {

    }

    @Override
    public boolean delete(Long projectId) {
        return false;
    }
/*
    HashMap<String, ProjectDetail> projectHashmap = new HashMap<>();
    @Override
    public boolean save(ProjectDetail projectDetail) {
        if(findProject(projectDetail.getProjectId()).isPresent())
            return false;
        projectHashmap.put(projectDetail.getProjectId(), projectDetail);
        return true;
    }

    @Override
    public void update(ProjectDetail projectDetail) {
        projectHashmap.replace(projectDetail.getProjectId(), projectDetail);
    }

    @Override
    public void delete(String projectId) {
        projectHashmap.remove(projectId);
    }

    @Override
    public Optional<ProjectDetail> findProject(String projectId){
        return projectHashmap.values().stream()
                .filter(project -> project.getProjectId().equals(projectId)).findAny();
    }

    @Override
    public Optional<List<ProjectDetail>> findAllProject(int userId) {
        List<ProjectDetail> projectDetail = projectHashmap.values().stream().filter(project -> project.getMemberList()
                .stream().anyMatch(x -> x == userId)).toList();
        return Optional.ofNullable(projectDetail.size() == 0 ? null : projectDetail);
    }

    @Override
    public int getProjectSize(){
        return projectHashmap.keySet().size();
    }
     */
}
