package com.toyproject.demo.dto.code.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class DeleteCodeBlockRequestDto {

    @NotNull
    private Long codeBlockId;

    public DeleteCodeBlockRequestDto(@NonNull Long codeBlockId) {
        this.codeBlockId = codeBlockId;
    }
}
