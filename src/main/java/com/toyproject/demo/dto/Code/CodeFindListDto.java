package com.toyproject.demo.dto.Code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CodeFindListDto {

        String title;
        String context;
        Long writerId;
        LocalDateTime writeDate;


}
