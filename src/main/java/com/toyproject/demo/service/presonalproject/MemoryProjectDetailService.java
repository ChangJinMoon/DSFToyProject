package com.toyproject.demo.service.presonalproject;

import com.toyproject.demo.domain.Sprint;
import com.toyproject.demo.repository.project.ProjectDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoryProjectDetailService implements ProjectDetailService{

    private final ProjectDetailRepository projectDetailRepository;


    @Override
    public List<Sprint> find(Long projectNum) {
        return projectDetailRepository.find(projectNum);
    }

    @Override
    public Long save(Long projectNum, Sprint sprint) {
        return projectDetailRepository.save(projectNum,sprint);
    }

    @Override
    public Long delete(Long sprintId) {
        Long deleteId = projectDetailRepository.deleteSprint(sprintId);
        return deleteId;
    }

    @Override
    public List<Sprint> findById(Long sprintId) {
        List<Sprint> sprint = projectDetailRepository.findSprint(sprintId);
        return sprint;
    }

    public List<Sprint> findByName(String name){
        List<Sprint> sprints = new ArrayList<>();

        List<Sprint> store = projectDetailRepository.findAll();

        for (Sprint sprint : store) {
            if(sprint.getName().contains(name)){
                sprints.add(sprint);
            }
        }

        return sprints;

    }
}
