package com.toyproject.demo.dto.code.response;

import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.code.CodeBlock;
import com.toyproject.demo.dto.code.CodeBlockDto;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CodeInitResponseDto {

    private String sprintName;

    private String sprintDetail;

    private LocalDateTime date;

    private List<CodeBlockDto> codeBlocks;

    public CodeInitResponseDto createInitResponseDto(Code code){
        CodeInitResponseDto temp = new CodeInitResponseDto();
        temp.codeBlocks = new ArrayList<>();
        temp.sprintName = code.getSprintName();
        temp.sprintDetail = code.getSprintDetail();
        temp.date = code.getLocalDateTime();
        code.getCodeBlockList().stream()
                .forEach(codeBlock -> temp.codeBlocks.add(CodeBlockDto.CodeBlockToDto(codeBlock)));
        return temp;
    }
}
