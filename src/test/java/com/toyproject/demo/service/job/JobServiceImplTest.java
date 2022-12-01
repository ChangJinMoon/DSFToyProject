package com.toyproject.demo.service.job;

import com.toyproject.demo.Message;
import com.toyproject.demo.domain.job.Job;
import com.toyproject.demo.domain.job.JobList;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.job.JobDto;
import com.toyproject.demo.dto.job.request.*;
import com.toyproject.demo.dto.job.response.AddJobWorkerResponseDto;
import com.toyproject.demo.dto.job.response.DeleteJobResponseDto;
import com.toyproject.demo.dto.job.response.JobInitResponseDto;
import com.toyproject.demo.dto.job.response.SaveJobResponseDto;
import com.toyproject.demo.repository.code.CodeRepository;
import com.toyproject.demo.repository.member.MysqlMemberRepository;
import com.toyproject.demo.repository.project.ProjectJpaRepository;
import com.toyproject.demo.repository.sprint.SprintJpaRepository;
import com.toyproject.demo.service.ServiceTestDomain;
import com.toyproject.demo.service.code.CodeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JobServiceImplTest {
    @Autowired MysqlMemberRepository memberRepository;
    @Autowired ProjectJpaRepository projectRepository;
    @Autowired SprintJpaRepository sprintRepository;
    @Autowired JobService jobService;
    private ServiceTestDomain testDomain = new ServiceTestDomain();
    private Member testMember,testMember2,testMember3;
    private ProjectDetail testProject;
    private Sprint testSprint;
    private Sprint testSprint2;
    private Job job;

    void beforeAll() {
        //make member
        testMember = testDomain.makeMember("jin1004boy@naver.com");
        memberRepository.save(testMember);

        testMember2 = testDomain.makeMember("test@naver.com");
        memberRepository.save(testMember2);

        testMember3 = testDomain.makeMember("test2@naver.com");
        memberRepository.save(testMember3);

        //make project
        testProject = testDomain.makProjectDetail(testMember);
        projectRepository.save(testProject);

        //make sprint
        testSprint = testDomain.makeSprint(testProject,2);
        sprintRepository.save(testSprint);

        testSprint2 = testDomain.makeSprint(testProject,2);
        sprintRepository.save(testSprint2);

    }

    @Test
    void init() {
        //given
        //beforeAll();
        Message<JobInitResponseDto> result = jobService.init(testSprint.getId());
        //when
        List<JobDto> list = result.getData().getList();
        //then
        list.forEach(job-> System.out.println(job.toString()));
    }

    @Test
    @Rollback(value = false)
    void saveJob() {
        //given
        beforeAll();
        List<Long> workerList = new ArrayList<>();
        workerList.add(testMember.getId());
        SaveJobRequestDto dto = new SaveJobRequestDto(testSprint.getId(), "do test", "test jobService code",
                LocalDateTime.now(), LocalDateTime.now(), workerList);
        SaveJobRequestDto dto2 = new SaveJobRequestDto(testSprint.getId(), "do test", "test jobService code",
                LocalDateTime.now(), LocalDateTime.now(), workerList);
        //when
        SaveJobResponseDto data = jobService.saveJob(dto).getData();
        SaveJobResponseDto data2 = jobService.saveJob(dto2).getData();
        //then
    }

    @Test
    @Rollback(value = false)
    void deleteJob() {
        saveJob();
        //given
        JobInitResponseDto result = jobService.init(testSprint.getId()).getData();
        DeleteJobRequestDto dto = new DeleteJobRequestDto(result.getList().get(0).getJobId());
        //when
        DeleteJobResponseDto data = jobService.deleteJob(dto).getData();
        //then
        System.out.println(data.getDeletedJob().toString());
    }

    @Test
    @Rollback(value = false)
    void addWorker() {
        //given
        saveJob();
        AddJobWorkerRequestDto dto = new AddJobWorkerRequestDto(1L, testMember2.getId());
        //when
        JobDto result = jobService.addWorker(dto).getData().getJobDto();
        //
        result.getJobWorkers().forEach(worker -> System.out.println(worker.getEmail()));
    }

    @Test
    void addWorkers() {
        //given
        saveJob();
        List<Long> workerList = new ArrayList<>();
        workerList.add(testMember2.getId()); workerList.add(testMember3.getId());
        AddJobWorkersRequestDto dto = new AddJobWorkersRequestDto(1L, workerList);
        //when
        JobDto result = jobService.addWorkers(dto).getData().getJobDto();
        //then
        result.getJobWorkers().forEach(worker-> System.out.println(worker.getEmail()));
    }

    @Test
    void deleteWorker() {
        //given
        saveJob();

    }

    @Test
    void updateJobDetail() {

    }

    @Test
    void updateJobDate() {

    }
}