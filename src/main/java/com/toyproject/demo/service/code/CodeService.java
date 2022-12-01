package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.dto.code.request.DeleteCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.SaveCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.UpdateCodeBlockRequestDto;
import com.toyproject.demo.dto.code.response.CodeInitResponseDto;
import com.toyproject.demo.dto.code.response.DeleteCodeBlockResponseDto;
import com.toyproject.demo.dto.code.response.SaveCodeBlockResponseDto;
import com.toyproject.demo.dto.code.response.UpdateCodeBlockResponseDto;

public interface CodeService {
    Message<CodeInitResponseDto> init(Long sprintId);

    Message<SaveCodeBlockResponseDto> saveCodeBlock(SaveCodeBlockRequestDto dto);

    Message<UpdateCodeBlockResponseDto> updateCodeBlock(UpdateCodeBlockRequestDto dto);

    Message<DeleteCodeBlockResponseDto> deleteCodeBlock(DeleteCodeBlockRequestDto dto);
}
