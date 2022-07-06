package com.toyproject.demo.service.presonalproject;

import com.toyproject.demo.Message;
import com.toyproject.demo.StatusEnum;
import com.toyproject.demo.domain.MakeHashKey;
import com.toyproject.demo.domain.Project;
import com.toyproject.demo.dto.personalpage.PersonalPageUpdateRequestDto;
import com.toyproject.demo.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PersonalProjectServiceImpl implements PersonalProjectService{
    private final ProjectRepository projectRepository;
    private final MakeHashKey makeHashKey;

    @Override
    public Message<List<Project>> init(int userid) {
        Message<List<Project>> message;
        //Bad Authorization

        Optional<List<Project>> allProject = projectRepository.findAllProject(userid);
        if(allProject.isPresent()) {
            message = new Message<>(StatusEnum.OK);
            message.setData(allProject.get());
        }
        else {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("user Project is empty");
        }
        return message;
    }

    @Override
    public Message<List<Project>> addProject(int userId, Project project) {
        Message<List<Project>> message;
        //check userId exist

        if(project.getProjectName() == null) {
            message = new Message<>(StatusEnum.BAD_REQUEST_NOT_ENOUGH);
            message.setMessage("Need ProjectName");
        }
        else {
            project.setProjectId(makeHashKey.makeProjectHashKey(projectRepository.getProjectSize()));
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

    @Override
    public Message<List<Project>> deleteProject(String projectId) {
        Message<List<Project>> message;
        //Bad Authorization

        Optional<Project> project = projectRepository.findProject(projectId);
        if(!project.isPresent()) {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("Project was not found");
        }
        else{
            projectRepository.delete(projectId);
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
    public Message<List<Project>> addMemberToProject(int userid ,String projectId) {
        Message<List<Project>> message;
        Optional<Project> project = projectRepository.findProject(projectId);
        if(!project.isPresent()){
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("ProjectID is Wrong");
            return message;
        }
        // check user Authority

        //check invitekey

        //then
        project.get().getMemberList().add(userid);
        projectRepository.update(project.get());

        if(projectRepository.findProject(projectId).get().getMemberList()
                .stream().anyMatch(memberId -> memberId == userid)) {
            message = new Message<>(StatusEnum.OK);
            log.info("Success");
        }
        else {
            message = new Message<>(StatusEnum.INTERNAL_SERVER_ERROR);
            log.error(getClass() + "-addMemberToProject: Repository problem occur");
        }
        return message;
    }

    @Override
    public Message<List<Project>> updateProject(String projectId,
                                                    PersonalPageUpdateRequestDto personalPageUpdateRequestDto) {
        Message<List<Project>> message;
        Optional<Project> project = projectRepository.findProject(projectId);
        if(!project.isPresent()) {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("ProjectID is Wrong");
        }
        else if(personalPageUpdateRequestDto.getProjectName() == null ||
                personalPageUpdateRequestDto.getProjectDetails() == null){
            message = new Message<>(StatusEnum.BAD_REQUEST_NOT_ENOUGH);
            log.error(getClass() + "-updateProject: Cilent error " + StatusEnum.BAD_REQUEST_NOT_ENOUGH);
        }
        else {
            Project temp = project.get();
            temp.setProjectName(personalPageUpdateRequestDto.getProjectName());
            temp.setProjectDetail(personalPageUpdateRequestDto.getProjectDetails());
            projectRepository.update(temp);

            project = projectRepository.findProject(projectId);

            if(project.get().getProjectName().equals(personalPageUpdateRequestDto.getProjectName())
                    && project.get().getProjectDetail().equals(personalPageUpdateRequestDto.getProjectDetails()))
                message = new Message<>(StatusEnum.OK);
            else {
                message = new Message<>(StatusEnum.INTERNAL_SERVER_ERROR);
                log.error(getClass() + "-updateProject: Repository problem occur");
            }
        }
        return message;
    }
    //project 관리에서 사용하기
    @Override
    public Message<List<Project>> deleteMemberFromProject(int userId, String projectId) {
        Message<List<Project>> message;
        Optional<Project> project = projectRepository.findProject(projectId);
        if(!project.isPresent()) {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("ProjectID is Wrong");
        }
        else if(!project.get().getMemberList().stream().anyMatch(memberId -> memberId == userId)) {
            message = new Message<>(StatusEnum.NOT_FOUND);
            message.setMessage("User is not belong that project");
        }
        else {
            if (project.get().getMemberList().removeIf(memberId -> memberId == userId)) {
                projectRepository.update(project.get());
                message = new Message<>(StatusEnum.OK);
            }
            else {
                message = new Message<>(StatusEnum.INTERNAL_SERVER_ERROR);
                log.error(getClass() + "-deleteMemberFromProject: Repository problem occur");
            }
        }
        return message;
    }

}
