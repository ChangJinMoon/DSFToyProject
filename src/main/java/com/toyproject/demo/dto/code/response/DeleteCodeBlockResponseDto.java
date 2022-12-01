package com.toyproject.demo.dto.code.response;

import lombok.Getter;

@Getter
public class DeleteCodeBlockResponseDto {

    private Long deletedCodeBlockId;

    public DeleteCodeBlockResponseDto(Long deletedCodeBlockId) {
        this.deletedCodeBlockId = deletedCodeBlockId;
    }
}
