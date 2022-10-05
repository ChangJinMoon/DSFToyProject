package com.toyproject.demo.repository.code;


import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.dto.Code.CodeDto;
import com.toyproject.demo.dto.Code.CodeFindListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface CodeRepository {

    Long save(Code code);
    Code findById(Long id);
    List<CodeFindListDto> findBySprintId(Long id);
    Long updateCode(Code code);
    Long deleteCode(Long id);


}
