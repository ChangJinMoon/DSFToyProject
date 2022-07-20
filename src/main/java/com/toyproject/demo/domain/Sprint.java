package com.toyproject.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Sprint {

    private Long id;
    private String name;
    private String details;
    private String context; // 설계도에 있어서 구현
    private List<Long> coworkers;
    private LocalDateTime localDateTime;
    private Long projectNum;



}
