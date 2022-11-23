package com.toyproject.demo.domain.code;


import com.toyproject.demo.domain.personalpage.ProjectDetail;
import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.code.CodeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.collection.internal.PersistentList;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: 2022/11/19 make codeBlock add method
@Entity
@Getter
@Table(name = "code_list")
public class Code extends Sprint{
    private Long writerId;

    @OneToMany(mappedBy = "codeGroup" , cascade = CascadeType.ALL)
    private List<CodeBlock> codeBlockList = new ArrayList<>();

    @Override
    public Code createSprint(String sprintName, String sprintDetail, ProjectDetail projectDetail) {
        Code code = new Code();
        code.sprintName = sprintName;
        code.sprintDetail = sprintDetail;
        code.project = projectDetail;
        code.localDateTime = LocalDateTime.now();
        return code;
    }

    @Override
    public void updateSprint(String sprintName, String sprintDetail) {

    }
}
