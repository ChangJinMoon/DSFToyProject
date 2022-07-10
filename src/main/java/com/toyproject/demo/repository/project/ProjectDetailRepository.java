package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.Sprint;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProjectDetailRepository {

    List<Sprint> find(Long projectNum); // 프로젝트 아이디에 속한 스프린트를 찾아줌.
    Long save(Long projectNum,Sprint sprint);
    Long deleteSprint(Long sprintId);
    List<Sprint> findSprint(Long sprintId);


}
