package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.Project;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectMemoryRepository implements ProjectRepository{
    HashMap<String,Project> projectHashmap = new HashMap<>();
    @Override
    public boolean save(Project project) {
        if(findProject(project.getProjectId()).isPresent())
            return false;
        projectHashmap.put(project.getProjectId(), project);
        return true;
    }

    @Override
    public Optional<List<Project>> findAllProject(int userId) {
        List<Project> projects = projectHashmap.values().stream().filter(project -> project.getMemberList()
                .stream().anyMatch(x -> x == userId)).toList();
        return Optional.ofNullable(projects.size() == 0 ? null : projects);
    }

    @Override
    public void delete(String projectId) {
        projectHashmap.remove(projectId);
    }

    @Override
    public void update(Project project) {
        projectHashmap.replace(project.getProjectId(),project);
    }

    @Override
    public Optional<Project> findProject(String projectId){
       return projectHashmap.values().stream()
               .filter(project -> project.getProjectId().equals(projectId)).findAny();
    }

    @Override
    public int getProjectSize(){
        return projectHashmap.keySet().size();
    }
}
