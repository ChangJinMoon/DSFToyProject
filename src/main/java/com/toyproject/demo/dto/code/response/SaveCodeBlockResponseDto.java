package com.toyproject.demo.dto.code.response;

import com.toyproject.demo.dto.code.CodeBlockDto;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class SaveCodeBlockResponseDto {
    private CodeBlockDto codeBlockDto;

    public SaveCodeBlockResponseDto(CodeBlockDto codeBlockDto) {
        this.codeBlockDto = codeBlockDto;
    }
}
