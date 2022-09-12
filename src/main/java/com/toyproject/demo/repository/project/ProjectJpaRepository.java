package com.toyproject.demo.repository.project;

import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectJpaRepository implements ProjectRepository{

    private final EntityManager em;

    @Override
    public boolean save(ProjectDetail project) {
        em.persist(project);
        return true;
    }

    @Override
    public Optional<ProjectDetail> findProject(Long projectId) {
        return Optional.ofNullable(em.find(ProjectDetail.class, projectId));
    }

    @Override
    public Optional<List<ProjectDetail>> findAllProject(Long userId) {
        List<MemberProject> memberProjectList = em.createQuery("select mp from MemberProject mp where mp.member.id = :userId", MemberProject.class)
                .setParameter("userId", userId)
                .getResultList();

        List<ProjectDetail> list = new ArrayList<>();
        memberProjectList.stream().forEach(memberProject -> list.add(memberProject.getProject()));
        return Optional.ofNullable(list);
    }

    @Override
    public void update(ProjectDetail project) {
        if(project.getProjectId() != null)
            em.merge(project);
    }

    @Override
    public boolean delete(Long projectId) {
        ProjectDetail projectDetail = em.find(ProjectDetail.class, projectId);
        if(projectDetail != null){
            em.remove(projectDetail);
            return true;
        }
        return false;
    }
}
