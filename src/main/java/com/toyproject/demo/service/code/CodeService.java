package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.dto.Code.CodeDto;
import com.toyproject.demo.dto.Code.CodeFindListDto;

import java.util.List;

public interface CodeService {
    Message<Long> save(CodeDto codeDto,Long sprintId);
    Message<List<CodeFindListDto>> getCodeList(Long id);
}
