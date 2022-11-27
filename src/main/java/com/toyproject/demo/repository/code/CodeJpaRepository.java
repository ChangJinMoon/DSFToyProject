package com.toyproject.demo.repository.code;

import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.code.CodeBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CodeJpaRepository implements CodeRepository{

    private final EntityManager entityManager;

    @Override
    public boolean save(Code code) {
        entityManager.persist(code);
        return true;
    }

    @Override
    public Optional<Code> findCodeById(Long id) {
        return Optional.ofNullable(entityManager.find(Code.class,id));
    }

    @Override
    public boolean updateCode(Code code) {
        entityManager.persist(code);
        return true;
    }

    @Override
    public boolean deleteCode(Code code) {
        entityManager.remove(code);
        return true;
    }

    @Override
    public Optional<CodeBlock> findCodeBlockById(Long codeBlockId) {
        return Optional.ofNullable(entityManager.find(CodeBlock.class,codeBlockId));
    }

    @Override
    public boolean updateCodeBlock(CodeBlock codeBlock) {
        entityManager.merge(codeBlock);
        return true;
    }

    @Override
    public boolean deleteCodeBlock(CodeBlock codeBlock) {
        entityManager.remove(codeBlock);
        return true;
    }
}
