package com.toyproject.demo.repository;

import com.toyproject.demo.domain.MakeHashKey;
import com.toyproject.demo.repository.project.ProjectMemoryRepository;
import com.toyproject.demo.repository.project.ProjectRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectMemoryRepositoryTest {
    ProjectRepository repository = new ProjectMemoryRepository();
    MakeHashKey makeHashKey = new MakeHashKey();

    @Test
    void save() {

    }

    @Test
    void findAllProject() {

    }

    @Test
    void delete() {

    }

    @Test
    void update() {

    }

    @Test
    void findProject() {

    }

    @Test
    void checkDoubleSave(){

    }
}