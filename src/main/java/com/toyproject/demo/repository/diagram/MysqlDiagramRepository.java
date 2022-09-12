package com.toyproject.demo.repository.diagram;

import com.toyproject.demo.domain.sprint.Diagram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MysqlDiagramRepository implements DiagramRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long save(Long projectId, Diagram diagram) {
        diagram.setLocalDateTime(LocalDateTime.now());
        em.persist(diagram);
        return diagram.getId();
    }

    @Override
    public String setSuperClass(String superClassName, Long SprintNum, Long diagramNum) {
        return null;
    }

    @Override
    public Optional<Diagram> findByNum(Long diagramNum) {

        try {
            Diagram diagram = em.find(Diagram.class, diagramNum);
            Optional<Diagram> findDiagram = Optional.of(diagram);
            return findDiagram;

        } catch (IllegalArgumentException e) {
            Diagram diagram = new Diagram(-1L);
            Optional<Diagram> notFindDiagram = Optional.of(diagram);
            return notFindDiagram;
        }

    }

    @Override
    public List<Diagram> findByProjectNum(Long projectNum) {
        List<Diagram> diagramList = findAll();
        List<Diagram> findByProjectNumDiagram = new ArrayList<>();

        for (Diagram diagram : diagramList) {
            if(projectNum == diagram.getProject().getProjectId()){
                findByProjectNumDiagram.add(diagram);
            }

        }
        return findByProjectNumDiagram;
    }

    @Override
    public List<Diagram> findAll() {
        List<Diagram> diagramList = em.createQuery("SELECT d from Diagram d", Diagram.class).getResultList();
        return diagramList;
    }
}
