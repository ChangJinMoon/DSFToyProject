package com.toyproject.demo.service.diagram;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.sprint.Diagram;
import com.toyproject.demo.repository.diagram.DiagramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MysqlDiagramService implements DiagramService{

    private final DiagramRepository diagramRepository;


    @Override
    @Transactional
    public Message<Long> save(Long projectNum, Diagram diagram) {
        Message<Long> message = new Message<>();

        Long saveId = diagramRepository.save(projectNum, diagram);
        message.setMessage("Diagram 저장");
        message.setStatusEum(StatusEnum.OK);
        message.setData(saveId);

        return message;
    }

    @Override
    public Message<Diagram> findById(Long diagramNum) {
        Message<Diagram> message = new Message<>();

        Diagram findDiagram = diagramRepository.findByNum(diagramNum).get();

        if(findDiagram.getId() == -1L){
            message.setMessage("유효하지 않은 아이디");
            message.setStatusEum(StatusEnum.OK);
        }
        else {
            message.setMessage("Diagram -> findById 성공");
            message.setStatusEum(StatusEnum.OK);
            message.setData(findDiagram);
        }

        return message;


    }

    @Override
    public Message<List<MultiValueMap>> makeDiagrams(List<Diagram> diagramListByProjectNum, Long projectNum) {
        Message<List<MultiValueMap>> message = new Message<>();
        List<Diagram> diagramList = diagramRepository.findByProjectNum(projectNum);
        List<MultiValueMap> graphs = new ArrayList<>();

        for (Diagram diagram : diagramList) {
            if(diagram.getSuperClassName() == null){
                MultiValueMap<Long,List> mtMap = makeGraph(diagram.getClassName());
                graphs.add(mtMap);
            }
        }

        message.setData(graphs);
        message.setStatusEum(StatusEnum.OK);
        message.setMessage("그래프 생현");


        return message;
    }

        private MultiValueMap<Long,List> makeGraph(String superClassName) {
        Queue<Graph> q = new LinkedList<>();
        MultiValueMap<Long,List> mtMap = new LinkedMultiValueMap<>();
        List<String> firstPair = new ArrayList<>();

        firstPair.add("None");
        firstPair.add(superClassName);

        mtMap.add(0L,firstPair);
        System.out.println(mtMap);
        System.out.println("----");

        q.add(new Graph(0L,superClassName));
        List<Diagram> all = diagramRepository.findAll();


        while (!q.isEmpty()){
            Graph graph = q.poll();

            for (Diagram diagram : all) {
//                System.out.println(diagram.toString());
//                System.out.println(graph.toString());
//                System.out.println("---------");

                if(diagram.getSuperClassName() != null && diagram.getSuperClassName().equals(graph.getName())){
                    List<String> pair = new ArrayList<>();

                    q.add(new Graph(graph.getLevel() + 1L, diagram.getClassName()));
                    System.out.println(graph.getLevel() + 1L);
                    System.out.println("---------");
                    pair.add(diagram.getSuperClassName());
                    pair.add(diagram.getClassName());
                    System.out.println(pair);
                    System.out.println(mtMap);
                    mtMap.add(graph.getLevel() + 1L, pair);
                    System.out.println(mtMap);

                }
            }
        }


        return mtMap;



    }
}
