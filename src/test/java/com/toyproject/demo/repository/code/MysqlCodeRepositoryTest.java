package com.toyproject.demo.repository.code;

import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.sprint.Sprint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MysqlCodeRepositoryTest {

    @Autowired
    CodeRepository codeRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback
    void save() {

        // given

        Code code = new Code();
        code.setTitle("TestTitle");
        code.setContext("TestCode");
        code.setWriterId(1L);
        Sprint sprint = new Sprint();

        code.setSprint(sprint);

        //when
        Long saveId = codeRepository.save(code);

        //then

        Code findCode = em.find(Code.class, saveId);

        Assertions.assertThat(code).isEqualTo(findCode);


    }

    @Test
    void findById() {

        // given

        Code code = new Code();
        code.setTitle("TestTitle");
        code.setContext("TestCode");
        code.setWriterId(1L);

        Sprint sprint = new Sprint();
        code.setSprint(sprint);
        Long saveId = codeRepository.save(code);

        //when
        Code findCode = codeRepository.findById(code.getId());

        //then
        Assertions.assertThat(code).isEqualTo(findCode);

    }

    @Test
    void findBySprintId() {
    }

    @Test
    void updateCode() {
    }

    @Test
    void deleteCode() {
    }
}