package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.dto.Code.CodeDto;
import com.toyproject.demo.dto.Code.CodeFindListDto;
import com.toyproject.demo.repository.code.CodeRepository;
import com.toyproject.demo.repository.sprint.SprintRepository;
import com.toyproject.demo.service.sprint.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
