package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.dto.Code.CodeDto;

import java.util.List;

public interface CodeService {
    Message<Long> save(CodeDto codeDto);
    Message<List<Code>> getCodeList(Long id);
}
