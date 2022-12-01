package com.toyproject.demo.service.projectDetail;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.job.JobList;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.invite.InviteRequestDto;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.dto.projectDetail.ProjectDetailAddRequestDto;
import com.toyproject.demo.dto.projectDetail.response.PersonalProjectGetOneResponseDto;
import com.toyproject.demo.dto.sprint.SprintInitDto;
import com.toyproject.demo.repository.member.MemberRepository;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.repository.sprint.SprintJpaRepository;
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
public class ProjectDetailServiceImpl implements ProjectDetailService{

    private final ProjectJpaRepository projectRepository;
    private final MemberRepository memberRepository;
    private final SprintJpaRepository sprintRepository;
    private final SprintInitDto sprintInitDto = new SprintInitDto();

    @Override
    public Message<List<SprintInitDto>> init(Long projectId) {
        Message<List<SprintInitDto>> message;
        //check Authorization - every Member
        //get SprintsInfo from Repository
        Optional<List<Sprint>> sprintList = sprintRepository.findAll(projectId);
        if(sprintList.isEmpty()){
            //wrong projectId
            if(projectRepository.findProject(projectId).isEmpty()) {
                message = new Message<>(StatusEnum.NOT_FOUND);
                message.setMessage("wrong projectId");
                return message;
            }
            //or empty
            message = new Message<>(StatusEnum.OK);
            message.setMessage("sprint-empty");
        }
        else{
            message = new Message<>(StatusEnum.OK);
            List<SprintInitDto> list = new ArrayList<>();
            sprintList.get().stream().forEach(sprint -> list.add(sprintInitDto.transSprintInitDto(sprint)));
            message.setData(list);
        }
        return message;
    }

    @Override
    public Message<String> deleteProject(Long projectId) {
        Message<String> message;
        //Bad Authorization

        if(projectRepository.delete(projectId) == false) {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("Project was not found");
        }
        else{
            if(projectRepository.findProject(projectId).isPresent()) {
                message = new Message<>(StatusEnum.INTERNAL_SERVER_ERROR);
                log.error(getClass() + "-deleteProject: Repository problem occur");
            }
            else
                message = new Message<>(StatusEnum.OK);
        }
        return message;
    }

    @Override
    public Message<ProjectDetail> addMemberToProject(Long userid , Long projectId) {
        Message<ProjectDetail> message;
        Optional<ProjectDetail> project = projectRepository.findProject(projectId);
        if(!project.isPresent()){
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("ProjectID is Wrong");
            return message;
        }
        // check user Authority

        //check invitekey

        //then
        Member member = memberRepository.findMember(userid).get();
        project.get().addMember(member);
        projectRepository.update(project.get());

        message = new Message<>(StatusEnum.OK);
        log.info("Success");
        return message;
    }

    @Override
    public Message<String> updateProject(Long projectId,
                                                PersonalPageUpdateRequestDto personalPageUpdateRequestDto) {
        Message<String> message;
        Optional<ProjectDetail> project = projectRepository.findProject(projectId);
        if(!project.isPresent()) {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("ProjectID is Wrong");
        }
        else if(personalPageUpdateRequestDto.getProjectName() == null &&
                personalPageUpdateRequestDto.getProjectDetails() == null){
            message = new Message<>(StatusEnum.BAD_REQUEST_NOT_ENOUGH);
            log.error(getClass() + "-updateProject: Cilent error " + StatusEnum.BAD_REQUEST_NOT_ENOUGH);
        }
        else {
            project.get().changeNameOrDetail(personalPageUpdateRequestDto.getProjectName()
                    ,personalPageUpdateRequestDto.getProjectDetails());
            projectRepository.update(project.get());
            message = new Message<>(StatusEnum.OK);
        }
        return message;
    }
    //project 관리에서 사용하기
    @Override
    public Message<ProjectDetail> deleteMemberFromProject(Long userId, Long projectId) {
        Message<ProjectDetail> message;
        Optional<ProjectDetail> project = projectRepository.findProject(projectId);

        if(!project.isPresent()) {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("ProjectID is Wrong");
        }
        else{
            List<MemberProject> members = project.get().getMembers();
            members.stream().filter(memberProject -> memberProject.findMember(userId))
                    .forEach(memberProject -> members.remove(memberProject));
            message = new Message<>(StatusEnum.OK);
        }
        return message;
    }

    @Override
    public Message<String> addSprint(Long projectId, ProjectDetailAddRequestDto requestDto) {
        Message<String> message;
        //check Authorization - every Member
        //get Sprint from requestDto
        Optional<ProjectDetail> project = projectRepository.findProject(projectId);

        Sprint sprint = makeSprintDependOnType(requestDto.getType())
                .createSprint(requestDto.getSprintName(), requestDto.getSprintDetails(), project.get());

        //save Sprint in repository
        sprintRepository.save(sprint);
        //make response Message
        message = new Message<>(StatusEnum.OK);
        message.setMessage("Success");
        return message;
    }

    public Message<PersonalProjectGetOneResponseDto> getOne(Long projectId){
        Optional<ProjectDetail> project = projectRepository.findProject(projectId);
        Message<PersonalProjectGetOneResponseDto> result = new Message<>();

        if(project.isPresent()) {
            result.setStatusEum(StatusEnum.OK);
            result.setData(new PersonalProjectGetOneResponseDto().projectToDto(project.get()));
            return result;
        }
        result.setStatusEum(StatusEnum.NOT_FOUND);
        return result;
    }

    @Override
    public Message<String> makeInviteCode(Long userId, InviteRequestDto requestDto) {
        //check Authorization - only Host
        //make inviteCode
        //save repository
        //make responseMessage
        return null;
    }

    private Sprint makeSprintDependOnType(int type){
        switch (type){
            case 1:
                return new JobList();
            default:
                return new Code();
        }
    }
}
