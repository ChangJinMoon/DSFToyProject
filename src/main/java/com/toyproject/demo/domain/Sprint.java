package com.toyproject.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sprint {

    private Long id;
    private String name;
    private LocalDateTime localDateTime;
    private Long projectNum;

}
