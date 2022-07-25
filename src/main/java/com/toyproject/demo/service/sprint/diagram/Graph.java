package com.toyproject.demo.service.sprint.diagram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter @Setter @ToString
@AllArgsConstructor
public class Graph {
    private Long level;
    private String name;
}
