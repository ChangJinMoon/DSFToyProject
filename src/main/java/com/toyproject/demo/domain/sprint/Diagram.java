package com.toyproject.demo.domain.sprint;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Diagram {

    private Long id;
    private String className;
    private LocalDateTime localDateTime;
    private Long sprintNum;

    //다이어그램을 만들기 위한 이름
    private String superClassName;

}
