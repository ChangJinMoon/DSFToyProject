package com.toyproject.demo.repository.code;


import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.dto.code.CodeFindListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MysqlCodeRepository implements CodeRepository {

    private final EntityManager em;


    @Override
    public Long save(Code code){
        em.persist(code);
        return code.getId();
    }

    @Override
    public Code findById(Long id) {
        Code code = em.find(Code.class, id);
        return code;
    }

    @Override
    public List<CodeFindListDto> findBySprintId(Long id) {
        List<Code> codeList = em.createQuery("select c from Code c" +
                " where c.sprint.id in :sprint_id", Code.class).setParameter("sprint_id",id).getResultList();
        List<CodeFindListDto> codeFindListDtoList = new ArrayList<>();
        for (Code code : codeList) {
            CodeFindListDto codeFindListDto = new CodeFindListDto();
            codeFindListDto.setId(code.getId());
            codeFindListDto.setTitle(code.getTitle());
            codeFindListDto.setContext(code.getContext());
            codeFindListDto.setWriteDate(code.getWriteDate());
            codeFindListDtoList.add(codeFindListDto);
        }

        return codeFindListDtoList;
    }

    @Override
    public Long updateCode(Code code) {
        Code merge = em.merge(code);
        System.out.println(code.toString());
        return code.getId();
    }

    @Override
    public Long deleteCode(Long id) {
        Code code = findById(id);
        Long deleteId = code.getId();
        em.remove(code);
        return deleteId;
    }
}
