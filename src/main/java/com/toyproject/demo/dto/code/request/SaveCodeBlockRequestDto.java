package com.toyproject.demo.dto.code.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Getter
public class SaveCodeBlockRequestDto {

    private Long sprintId;

    private Long writerId;

    private String code;

    private String text;

    public SaveCodeBlockRequestDto(@NonNull Long sprintId, @NonNull Long writerId
            ,@NonNull String code,@NonNull String text) {
        this.sprintId = sprintId;
        this.writerId = writerId;
        this.code = code;
        this.text = text;
    }
}
