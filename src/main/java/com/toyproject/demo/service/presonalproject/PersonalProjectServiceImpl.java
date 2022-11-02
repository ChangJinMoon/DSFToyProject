package com.toyproject.demo.service.presonalproject;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.dto.personalpage.PersonalPageAddRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageInitDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.repository.member.MemberRepository;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PersonalProjectServiceImpl implements PersonalProjectService{
    private final ProjectJpaRepository projectRepository;
    private final MemberRepository memberRepository;
    private final PersonalPageInitDto personalPageInitDto = new PersonalPageInitDto();

    @Override
    public Message<List<PersonalPageInitDto>> init(Long userid) {
        Message<List<PersonalPageInitDto>> message;
        //Bad Authorization

        Optional<List<ProjectDetail>> allProject = projectRepository.findAllProject(userid);
        if(allProject.isPresent()) {
            message = new Message<>(StatusEnum.OK);
            List<PersonalPageInitDto> list = new ArrayList<>();
            allProject.get().stream()
                    .forEach(projectDetail -> list.add(personalPageInitDto.transPersonalPageInitDto(projectDetail)));
            message.setData(list);
        }
        else {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("user Project is empty");
        }
        return message;
    }

    @Override
    public Message<String> addProject(Long userId, PersonalPageAddRequestDto pageAddRequestDto) {
        Message<String> message;
        //check userId exist

        if(pageAddRequestDto.getProjectName() == null || pageAddRequestDto.getProjectDetails() == null) {
            message = new Message<>(StatusEnum.BAD_REQUEST_NOT_ENOUGH);
            message.setMessage("Need ProjectName");
        }
        else {

            ProjectDetail project = new ProjectDetail().createProject(pageAddRequestDto.getProjectName()
                    ,pageAddRequestDto.getProjectDetails(), userId,new MemberProject().createMemberProject(memberRepository.findMember(userId).get()));
            projectRepository.save(project);

            if(projectRepository.findProject(project.getProjectId()).isPresent()) {
                message = new Message<>(StatusEnum.OK);
                log.info("Success");
            }
            else {
                message = new Message<>(StatusEnum.INTERNAL_SERVER_ERROR);
                log.error(getClass() + "-addProject: Repository problem occur");
            }
        }
        return message;
    }
}
