package com.toyproject.demo.repository.sprint;

import com.toyproject.demo.domain.sprint.Sprint;

import java.util.List;
import java.util.Optional;

public interface SprintRepository {
    void save(Sprint sprint);
    Optional<Sprint> find(Long sprintId);
    Optional<List<Sprint>> findAll(Long projectId);
    void update(Sprint sprint);
    void delete(Sprint sprint);

}
