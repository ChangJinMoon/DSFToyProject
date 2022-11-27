package com.toyproject.demo.dto.code.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Getter
public class DeleteCodeBlockRequestDto {

    private Long codeBlockId;

    public DeleteCodeBlockRequestDto(@NonNull Long codeBlockId) {
        this.codeBlockId = codeBlockId;
    }
}
