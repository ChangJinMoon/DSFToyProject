package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.dto.code.CodeDto;
import com.toyproject.demo.dto.code.CodeFindListDto;
import com.toyproject.demo.dto.code.CodeUpdateDto;

import java.util.List;

public interface CodeService {
    Message<Long> save(CodeDto codeDto,Long sprintId);
    Message<List<CodeFindListDto>> getCodeList(Long id);
    Message<Long> update(CodeUpdateDto codeUpdateDto, Long codeId);
}
