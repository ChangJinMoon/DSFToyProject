package com.toyproject.demo.dto.Code;

import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.sprint.Sprint;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
public class CodeDto {

    String title;
    String context;
    Long writerId;

}
