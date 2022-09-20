package com.toyproject.demo.repository.sprint;

import com.toyproject.demo.domain.sprint.Sprint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SprintJpaRepository implements SprintRepository{

    private final EntityManager entityManager;

    @Override
    public void save(Sprint sprint) {
        entityManager.persist(sprint);
    }

    @Override
    public Optional<Sprint> find(Long sprintId) {
        return Optional.ofNullable(entityManager.find(Sprint.class,sprintId));
    }

    @Override
    public Optional<List<Sprint>> findAll(Long projectId) {
        return Optional.ofNullable(
                entityManager.createQuery("select s from Sprint s where s.project.projectId = :projectId", Sprint.class)
                .setParameter("projectId",projectId)
                .getResultList());
    }

    @Override
    public boolean update(Sprint sprint) {
        if(sprint.getId() == null) {
            return false;
        }
        entityManager.merge(sprint);
        return false;
    }

    @Override
    public boolean delete(Sprint sprint) {
        if(entityManager.find(Sprint.class,sprint.getId()) != null) {
            entityManager.remove(sprint);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
