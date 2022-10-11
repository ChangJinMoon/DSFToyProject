package com.toyproject.demo.controller;


import com.toyproject.demo.Message;
import com.toyproject.demo.dto.code.CodeDto;
import com.toyproject.demo.dto.code.CodeFindListDto;
import com.toyproject.demo.dto.code.CodeUpdateDto;
import com.toyproject.demo.service.code.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;


    @GetMapping("/code/{sprintId}")
    public Message<List<CodeFindListDto>> getCodeList(@PathVariable(name = "sprintId") Long sprintId){
        Message<List<CodeFindListDto>> codeList = codeService.getCodeList(sprintId);
        return codeList;
    }

    @PostMapping("/code/{sprintId}")
    public Message<Long> save(@PathVariable(name = "sprintId") Long sprintId, @RequestBody CodeDto codeDto){
        System.out.println(codeDto.toString());
        Message<Long> save = codeService.save(codeDto,sprintId);
        return save;
    }

    @PatchMapping("/code/{sprintId}/{codeId}")
    public Message<Long> update(@PathVariable Long codeId, @PathVariable String sprintId, @RequestBody CodeUpdateDto codeUpdateDto){
        Message<Long> update = codeService.update(codeUpdateDto, codeId);
        System.out.println(codeUpdateDto.toString());
        return update;
    }

    @DeleteMapping("/code/{sprintId}/{codeId}")
    public Message<Boolean> delete(@PathVariable Long codeId, @PathVariable String sprintId){
        Message<Boolean> delete = codeService.delete(codeId);
        return delete;
    }
}
