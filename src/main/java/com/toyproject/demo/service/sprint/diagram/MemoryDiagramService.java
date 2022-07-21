package com.toyproject.demo.service.sprint.diagram;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.sprint.Diagram;
import com.toyproject.demo.repository.sprint.diagram.DiagramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoryDiagramService implements DiagramService{

    private final DiagramRepository diagramRepository;

    @Override
    public Message<Long> save(Long sprintNum, Diagram diagram) {
        Message<Long> message = new Message<>();
        Long save = diagramRepository.save(sprintNum,diagram);
        message.setMessage("Diagram 저장");
        message.setStatusEum(StatusEnum.OK);
        message.setData(save);


        return message;

    }

    @Override
    public Message<Diagram> findById(Long diagramNum) {
        Message<Diagram> message = new Message<>();

        Diagram find = diagramRepository.findByNum(diagramNum);

        if(find != null){
            message.setMessage("Diagram 찾기 성공");
            message.setData(find);
            message.setStatusEum(StatusEnum.OK);
        }

        return message;
    }
}
