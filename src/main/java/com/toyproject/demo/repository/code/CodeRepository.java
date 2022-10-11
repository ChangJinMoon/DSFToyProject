package com.toyproject.demo.repository.code;


import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.dto.code.CodeFindListDto;

import java.util.List;

public interface CodeRepository {

    Long save(Code code);
    Code findById(Long id);
    List<CodeFindListDto> findBySprintId(Long id);
    Long updateCode(Code code);
    Long deleteCode(Long id);


}
