package com.toyproject.demo.service.code;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.code.CodeBlock;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.code.CodeBlockDto;
import com.toyproject.demo.dto.code.request.DeleteCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.SaveCodeBlockRequestDto;
import com.toyproject.demo.dto.code.request.UpdateCodeBlockRequestDto;
import com.toyproject.demo.dto.code.response.CodeInitResponseDto;
import com.toyproject.demo.dto.code.response.SaveCodeBlockResponseDto;
import com.toyproject.demo.repository.code.CodeRepository;
import com.toyproject.demo.repository.member.MysqlMemberRepository;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.repository.sprint.SprintJpaRepository;
import com.toyproject.demo.service.ServiceTestDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class CodeServiceImplTest {

    @Autowired MysqlMemberRepository memberRepository;
    @Autowired ProjectJpaRepository projectRepository;
    @Autowired SprintJpaRepository sprintRepository;
    @Autowired CodeService codeService;
    @Autowired CodeRepository codeRepository;
    private ServiceTestDomain testDomain = new ServiceTestDomain();
    private Member testMember;
    private ProjectDetail testProject;
    private Sprint testSprint;
    private Sprint testSprint2;

    void beforeAll() {
        //make member
        testMember = testDomain.makeMember("jin1004boy@naver.com");
        memberRepository.save(testMember);

        //make project
        testProject = testDomain.makProjectDetail(testMember);
        projectRepository.save(testProject);

        //make sprint
        testSprint = testDomain.makeSprint(testProject,1);
        sprintRepository.save(testSprint);

        testSprint2 = testDomain.makeSprint(testProject,1);
        sprintRepository.save(testSprint2);
    }

    @Test
    @Rollback(value = false)
    void init() {
        beforeAll();
        //given
        Message<CodeInitResponseDto> init = codeService.init(testSprint.getId());
        CodeInitResponseDto result = init.getData();
        //when
        //then
        System.out.println(result.getSprintName());
        System.out.println(result.getSprintDetail());
        System.out.println(result.getDate());
    }

    @Test
    @Rollback(value = false)
    void saveCodeBlock() {
        beforeAll();
        //given
        SaveCodeBlockRequestDto dto = new SaveCodeBlockRequestDto(testSprint.getId(),testMember.getId(),"#include stdio.h  int main(){ println(it is test) return 0;}","it is test");
        //when
        codeService.saveCodeBlock(dto);
        codeService.saveCodeBlock(dto);
        dto = new SaveCodeBlockRequestDto(testSprint2.getId(),testMember.getId(),"#include stdio.h  int main(){ println(it is test) return 0;}","it is test");
        codeService.saveCodeBlock(dto);
        Message<CodeInitResponseDto> response = codeService.init(testSprint2.getId());
        CodeInitResponseDto testResult = response.getData();
        List<CodeBlockDto> codeBlocks = testResult.getCodeBlocks();
        //then
        System.out.println(testResult.getSprintName());
        System.out.println(testResult.getSprintDetail());
        System.out.println(testResult.getDate());

        System.out.println(codeBlocks.size());
        System.out.println(codeBlocks.get(0).getCodeId());
        System.out.println(codeBlocks.get(0).getWriterId());
        System.out.println(codeBlocks.get(0).getCode());
        System.out.println(codeBlocks.get(0).getText());
        System.out.println(codeBlocks.get(0).getCreateDate());
    }

    @Test
    @Rollback(value = false)
    void updateCodeBlock() {
        beforeAll();
        //given
        SaveCodeBlockRequestDto dto = new SaveCodeBlockRequestDto(testSprint.getId(),testMember.getId(),"#include stdio.h  int main(){ println(it is test) return 0;}","it is test");
        Message<SaveCodeBlockResponseDto> saved = codeService.saveCodeBlock(dto);
        //when
        UpdateCodeBlockRequestDto updateDto = new UpdateCodeBlockRequestDto(saved.getData().getCodeBlockDto().getCodeId(),"fixe",null);
        CodeBlockDto updatedDto = codeService.updateCodeBlock(updateDto).getData().getCodeBlockDto();
        //then
        System.out.println(updatedDto.getCodeId());
        System.out.println(updatedDto.getWriterId());
        System.out.println(updatedDto.getCode());
        System.out.println(updatedDto.getText());
        System.out.println(updatedDto.getCreateDate());
    }

    @Test
    @Rollback(value = false)
    void deleteCodeBlock() {
        beforeAll();
        //given
        SaveCodeBlockRequestDto dto = new SaveCodeBlockRequestDto(testSprint.getId(),testMember.getId(),"#include stdio.h  int main(){ println(it is test) return 0;}","it is test");
        Message<SaveCodeBlockResponseDto> saved = codeService.saveCodeBlock(dto);
        //when
        Optional<CodeBlock> codeBlockById = codeRepository.findCodeBlockById(saved.getData().getCodeBlockDto().getCodeId());
        codeRepository.deleteCodeBlock(codeBlockById.get());
        //sprintRepository.delete(testSprint);

        //then


    }
}