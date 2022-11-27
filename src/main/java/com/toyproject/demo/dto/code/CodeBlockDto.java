package com.toyproject.demo.dto.code;

import com.toyproject.demo.domain.code.CodeBlock;

import java.time.LocalDateTime;

public class CodeBlockDto {

    private Long codeId;

    private Long writerId;

    private String code;

    private String text;

    private LocalDateTime createDate;

    public static CodeBlockDto CodeBlockToDto(CodeBlock codeBlock){
        CodeBlockDto temp = new CodeBlockDto();
        temp.codeId = codeBlock.getId();
        temp.writerId = codeBlock.getWriterId();
        temp.code = codeBlock.getCode();
        temp.text = codeBlock.getText();
        temp.createDate = codeBlock.getCreateDate();
        return temp;
    }
}
