package com.toyproject.demo.service.sprint;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.Sprint;
import com.toyproject.demo.repository.sprint.SprintRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemorySprintService implements SprintService{

    private final SprintRepository sprintRepository;

    @Override
    public Message<Long> save(Long projectNum, Sprint sprint) {
        Message<Long> message = new Message<>();
        if(projectNum != null){
            Long sprintId = sprintRepository.save(projectNum,sprint);
            message.setStatusEum(StatusEnum.OK);
            message.setData(sprintId);
            message.setMessage("성공적으로 Sprint 저장 성공");
            log.info("Sprint save 성공 : id -> {}",sprintId);

            return message;
        }
        message.setMessage("Sprint save 실패 -> projectNum이 존재하지 않음");
        message.setStatusEum(StatusEnum.BAD_REQUEST_NOT_ENOUGH);
        log.info("Sprint save 실패 projectNum 오류 : {}",projectNum);
        return message;
    }

    @Override
    public Message<List<Sprint>> getSprintByProject(Long projectNum) {
        Message<List<Sprint>> message = new Message<>();

        List<Sprint> sprints = sprintRepository.allSprintByProject(projectNum);

        if(sprints.isEmpty()){
            message.setMessage("Sprint가 존재하지 않음");
            log.info("스프린트가 존재하지 않음");
            return message;
        }

        message.setMessage("성공적으로 조회");
        message.setStatusEum(StatusEnum.OK);
        message.setData(sprints);

        return message;
    }
}
