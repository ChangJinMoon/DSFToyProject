package com.toyproject.demo.repository.sprint;

import com.toyproject.demo.domain.sprint.Sprint;

import java.util.List;
import java.util.Optional;

public interface SprintRepository {
    void save(Sprint sprint);
    Optional<Sprint> find(Long sprintId);
    Optional<List<Sprint>> findAll(Long projectId);
    boolean update(Sprint sprint);
    boolean delete(Sprint sprint);

}
