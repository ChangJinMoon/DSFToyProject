package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.dto.code.CodeDto;
import com.toyproject.demo.dto.code.CodeFindListDto;
import com.toyproject.demo.dto.code.CodeUpdateDto;
import com.toyproject.demo.repository.code.CodeRepository;
import com.toyproject.demo.repository.sprint.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private final CodeRepository codeRepository;
    private final SprintRepository sprintRepository;


    @Transactional
    @Override
    public Message<Long> save(CodeDto codeDto, Long sprintId) {

        Code code = CodeDto.DtoToEntity(codeDto);
        code.setSprint(sprintRepository.find(sprintId).get());
        Long saveId = codeRepository.save(code);

        if(saveId != null){
            Message<Long> message = new Message<>(StatusEnum.OK);
            message.setData(saveId);
            message.setMessage("Code가 성공적으로 저장되었습니다.");

            return message;
        }

        Message<Long> message = new Message<>(StatusEnum.INTERNAL_SERVER_ERROR);
        message.setMessage("알수없는 오류로 Code 저장이 실패했습니다.");
        message.setData(-1L);

        return message;
    }

    @Override
    public Message<List<CodeFindListDto>> getCodeList(Long id) {
        List<CodeFindListDto> codeFindListDtoList = codeRepository.findBySprintId(id);

        Message<List<CodeFindListDto>> message = new Message<>();

        if(codeFindListDtoList.isEmpty()){
            message.setMessage("Code가 존재하지 않음");
            message.setStatusEum(StatusEnum.OK);

            return message;
        }
        message.setMessage("Code를 불러옴");
        message.setStatusEum(StatusEnum.OK);
        message.setData(codeFindListDtoList);

        return message;
    }

    @Transactional
    @Override
    public Message<Long> update(CodeUpdateDto codeUpdateDto, Long codeId) {
        Code findCode = codeRepository.findById(codeId);
        findCode.setTitle(codeUpdateDto.getTitle());
        findCode.setContext(codeUpdateDto.getContext());
        findCode.setWriteDate(LocalDateTime.now());

//
//        Code updateCode = codeUpdateDto.DtoToEntity();
//        updateCode.setId(codeId);
//        Long updateCodeId = codeRepository.updateCode(updateCode);
//        System.out.println(updateCode.toString());

        Message<Long> message = new Message<>();
        message.setMessage("업데이트 완료");
        message.setData(findCode.getId());
        message.setStatusEum(StatusEnum.OK);

        return message;
    }

    @Transactional
    @Override
    public Message<Boolean> delete(Long codeId) {
        codeRepository.deleteCode(codeId);
        Message<Boolean> message = new Message<>();
        message.setMessage("삭제 완료");
        message.setData(Boolean.TRUE);
        message.setStatusEum(StatusEnum.OK);
        return message;
    }
}


