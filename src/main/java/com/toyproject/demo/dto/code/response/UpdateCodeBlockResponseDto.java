package com.toyproject.demo.dto.code.response;

import com.toyproject.demo.dto.code.CodeBlockDto;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class UpdateCodeBlockResponseDto {
    private CodeBlockDto codeBlockDto;

    public UpdateCodeBlockResponseDto(CodeBlockDto codeBlockDto) {
        this.codeBlockDto = codeBlockDto;
    }
}
