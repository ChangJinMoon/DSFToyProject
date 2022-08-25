package com.toyproject.demo.domain.sprint;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Diagram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @Column(nullable = false)
    private Long projectId;

    //다이어그램을 만들기 위한 이름
    @Column(nullable = true)
    private String superClassName;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Diagram() {
    }

    public Diagram(Long id) {
        this.id = id;
    }
}
