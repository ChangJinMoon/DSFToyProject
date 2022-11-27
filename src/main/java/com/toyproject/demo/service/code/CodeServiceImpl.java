package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.code.CodeBlock;
import com.toyproject.demo.dto.code.CodeBlockDto;
import com.toyproject.demo.dto.code.request.DeleteCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.SaveCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.UpdateCodeBlockRequestDto;
import com.toyproject.demo.dto.code.response.CodeInitResponseDto;
import com.toyproject.demo.dto.code.response.DeleteCodeBlockResponseDto;
import com.toyproject.demo.dto.code.response.SaveCodeBlockResponseDto;
import com.toyproject.demo.dto.code.response.UpdateCodeBlockResponseDto;
import com.toyproject.demo.repository.code.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private final CodeRepository repository;

    private CodeInitResponseDto codeInitResponseDto = new CodeInitResponseDto();

    @Override
    public Message<CodeInitResponseDto> init(Long sprintId) {
        Optional<Code> result = repository.findCodeById(sprintId);
        Message<CodeInitResponseDto> response = new Message<>();

        if(!result.isPresent()){
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("Sprint Id is Wrong");
            return response;
        }

        response.setStatusEum(StatusEnum.OK);
        response.setData(codeInitResponseDto.createInitResponseDto(result.get()));
        return response;
    }

    @Override
    public Message<SaveCodeBlockResponseDto> saveCodeBlock(SaveCodeBlockRequestDto dto) {
        Optional<Code> result = repository.findCodeById(dto.getSprintId());
        Message<SaveCodeBlockResponseDto> response = new Message<>();

        if(result.isEmpty()){
           response.setStatusEum(StatusEnum.NOT_FOUND);
           response.setMessage("Sprint Id is Wrong");
           return response;
        }
        //update
        CodeBlock codeBlock = CodeBlock
                .createCodeBlock(dto.getWriterId(), dto.getCode(), dto.getText());
        result.get()
                .addCodeBlockOnlyOne(codeBlock);
        boolean updateResult = repository.updateCode(result.get());

        if(updateResult == false){
            response.setStatusEum(StatusEnum.INTERNAL_SERVER_ERROR);
            response.setMessage("save failed");
            return response;
        }

        response.setStatusEum(StatusEnum.OK);
        response.setData(new SaveCodeBlockResponseDto(CodeBlockDto.CodeBlockToDto(codeBlock)));
        return response;
    }

    @Override
    public Message<UpdateCodeBlockResponseDto> updateCodeBlock(UpdateCodeBlockRequestDto dto) {
        Optional<CodeBlock> result = repository.findCodeBlockById(dto.getCodeBlockId());
        Message<UpdateCodeBlockResponseDto> response = new Message<>();

        if(result.isEmpty()){
                response.setStatusEum(StatusEnum.NOT_FOUND);
                response.setMessage("CodeBlock Id is Wrong");
                return response;
        }

        String updateCode = (dto.getCode().isEmpty() ? result.get().getCode() : dto.getCode());
        String updateText = (dto.getText().isEmpty() ? result.get().getText() : dto.getText());

        result.get().updateCodeBlock(updateCode,updateText);
        repository.updateCodeBlock(result.get());

        response.setStatusEum(StatusEnum.OK);
        response.setData(new UpdateCodeBlockResponseDto(CodeBlockDto.CodeBlockToDto(result.get())));
        return response;
    }

    @Override
    public Message<DeleteCodeBlockResponseDto> deleteCodeBlock(DeleteCodeBlockRequestDto dto) {
        Optional<CodeBlock> result = repository.findCodeBlockById(dto.getCodeBlockId());
        Message<DeleteCodeBlockResponseDto> response = new Message<>();

        if(result.isEmpty()){
            response.setStatusEum(StatusEnum.NOT_FOUND);
            response.setMessage("CodeBlock Id is Wrong");
            return response;
        }

        repository.deleteCodeBlock(result.get());

        response.setStatusEum(StatusEnum.OK);
        response.setData(new DeleteCodeBlockResponseDto(result.get().getId()));
        return response;
    }
}
