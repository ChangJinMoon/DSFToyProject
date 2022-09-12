package com.toyproject.demo.service.sprint;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.projectDetail.ProjectDetailDeleteRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailUpdateRequestDto;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.repository.sprint.SprintJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService{

    private final SprintJpaRepository sprintRepository;
    private final ProjectJpaRepository projectRepository;

    @Override
    public Message<Sprint> init(Long sprintId) {
        Message<Sprint> message;
        Optional<Sprint> sprint = sprintRepository.find(sprintId);
        //check sprintId is exist
        if(sprint.isEmpty()){
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("Wrong Sprint Id");
        }
        else{
            message = new Message<>(StatusEnum.OK);
            message.setMessage("Success");
            message.setData(sprint.get());
        }
        return message;
    }

    @Override
    public Message<String> deleteSprint(Long userId, ProjectDetailDeleteRequestDto requestDto) {
        Optional<Sprint> sprint = sprintRepository.find(requestDto.getSprintId());
        //check sprintId is exist
        if(sprint.isEmpty())
            return makeIdNotExistMessage();

        //check Authorization - only Host
        if(sprint.get().getProject().getProjectLeader() != userId)
           return makeIdNotAuthorizationMessage();

        Message<String> message;
        //delete Sprint in repository
        sprintRepository.delete(sprint.get());
        //make response Message
        message = new Message<>(StatusEnum.OK);
        message.setMessage("Success");
        return message;
    }

    @Override
    public Message<String> updateSprint(Long userId, ProjectDetailUpdateRequestDto requestDto) {
        Optional<Sprint> sprint = sprintRepository.find(requestDto.getSprintId());
        //check sprintId is exist
        if(sprint.isEmpty())
            return makeIdNotExistMessage();

        //check Authorization - only Host
        if(sprint.get().getProject().getProjectLeader() != userId)
            return makeIdNotAuthorizationMessage();

        Message<String> message;
        //patch Sprint in repository
        sprintRepository.update(sprint.get());
        //make response Message
        message = new Message<>(StatusEnum.OK);
        message.setMessage("Success");
        return message;
    }

    public Message<String> makeIdNotExistMessage(){
            Message<String> message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("Wrong Sprint Id");
            return message;
    }

    public Message<String> makeIdNotAuthorizationMessage(){
        Message<String> message = new Message<>(StatusEnum.BAD_REQUEST_AUTHORIZATION);
        message.setMessage("Wrong Authorization");
        return message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
