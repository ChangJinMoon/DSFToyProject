package com.toyproject.demo.dto.personalpage;

import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.member.Member;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalPageAddRequestDto {
    private String projectName;
    private String projectDetails;
}
