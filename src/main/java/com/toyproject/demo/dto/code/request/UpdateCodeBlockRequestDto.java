package com.toyproject.demo.dto.code.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class UpdateCodeBlockRequestDto {
    @NotNull
    private Long codeBlockId;

    @NotNull
    private String code;

    @NotNull
    private String text;

    public UpdateCodeBlockRequestDto(@NonNull Long codeBlockId, @Nullable String code
            , @Nullable String text) {
        this.codeBlockId = codeBlockId;
        this.code = (code == null ? "" : code);
        this.text = (text == null ? "" : text);
    }
}
