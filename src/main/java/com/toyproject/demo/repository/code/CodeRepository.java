package com.toyproject.demo.repository.code;


import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.code.CodeBlock;

import java.util.Optional;

public interface CodeRepository {

    boolean save(Code code);

    Optional<Code> findCodeById(Long id);

    Optional<CodeBlock> findCodeBlockById(Long codeBlockid);
    boolean updateCode(Code code);

    boolean deleteCode(Code code);

    boolean updateCodeBlock(CodeBlock codeBlock);

    boolean deleteCodeBlock(CodeBlock codeBlock);
}
