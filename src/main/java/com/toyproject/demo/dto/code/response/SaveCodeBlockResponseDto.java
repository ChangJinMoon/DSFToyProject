package com.toyproject.demo.dto.code.response;

import com.toyproject.demo.dto.code.CodeBlockDto;
import org.springframework.lang.Nullable;

public class SaveCodeBlockResponseDto {

    private boolean result;

    private CodeBlockDto codeBlockDto;

    public SaveCodeBlockResponseDto(CodeBlockDto codeBlockDto) {
        this.codeBlockDto = codeBlockDto;
    }
}
