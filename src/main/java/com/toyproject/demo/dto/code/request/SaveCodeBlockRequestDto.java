package com.toyproject.demo.dto.code.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class SaveCodeBlockRequestDto {

    @NotNull
    private Long sprintId;

    @NotNull
    private Long writerId;

    @NotNull
    private String code;

    @NotNull
    private String text;

    public SaveCodeBlockRequestDto(@NonNull Long sprintId, @NonNull Long writerId
            ,@NonNull String code,@NonNull String text) {
        this.sprintId = sprintId;
        this.writerId = writerId;
        this.code = code;
        this.text = text;
    }
}
