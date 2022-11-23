package com.toyproject.demo.dto.code;

import com.toyproject.demo.domain.code.Code;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CodeDto {

    String title;
    String context;
    Long writerId;


}
