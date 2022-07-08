package com.toyproject.demo.service.presonalproject;

import com.toyproject.demo.domain.Sprint;
import com.toyproject.demo.repository.project.ProjectDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
