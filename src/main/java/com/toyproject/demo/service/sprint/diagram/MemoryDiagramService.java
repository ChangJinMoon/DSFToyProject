package com.toyproject.demo.service.sprint.diagram;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.sprint.Diagram;
import com.toyproject.demo.repository.sprint.diagram.DiagramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    @Override
    public Message<List<MultiValueMap>> makeDiagrams(List<Diagram> diagramListBySprintNum,Long sprintNum) {
        Message<List<MultiValueMap>> message = new Message<>();
        List<Diagram> diagramList = diagramRepository.findBySprintNum(sprintNum);
        List<MultiValueMap> graphs = new ArrayList<>();
        for (Diagram diagram : diagramList) {
            if(diagram.getSuperClassName() == null){
                MultiValueMap<Long,String> mtMap = makeGraph(diagram.getClassName());
                graphs.add(mtMap);
            }
        }

        message.setData(graphs);
        message.setStatusEum(StatusEnum.OK);
        message.setMessage("그래프 생성");


        return message;

    }

    private MultiValueMap<Long,String> makeGraph(String superClassName) {
        Queue<Graph> q = new LinkedList<>();
        MultiValueMap<Long,String> mtMap = new LinkedMultiValueMap<>();
        mtMap.add(0L,superClassName);

        q.add(new Graph(0L,superClassName));
        List<Diagram> all = diagramRepository.findAll();
        while (!q.isEmpty()){
            Graph graph = q.poll();

            for (Diagram diagram : all) {
                System.out.println(diagram.toString());
                System.out.println(graph.toString());
                System.out.println("---------");
                if(diagram.getSuperClassName() != null && diagram.getSuperClassName().equals(graph.getName())){
                    q.add(new Graph(graph.getLevel() + 1L, diagram.getClassName()));
                    System.out.println(graph.getLevel() + 1L);
                    mtMap.add(graph.getLevel() + 1L, diagram.getClassName());
                }
            }
        }


        return mtMap;



    }
}
