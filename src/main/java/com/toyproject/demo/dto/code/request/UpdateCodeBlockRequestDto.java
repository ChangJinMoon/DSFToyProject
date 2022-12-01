package com.toyproject.demo.dto.code.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@Getter
public class UpdateCodeBlockRequestDto {
    private Long codeBlockId;

    private String code;

    private String text;

    public UpdateCodeBlockRequestDto(@NonNull Long codeBlockId, @Nullable String code
            , @Nullable String text) {
        this.codeBlockId = codeBlockId;
        this.code = (code == null ? "" : code);
        this.text = (text == null ? "" : text);
    }
}
