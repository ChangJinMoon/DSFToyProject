package com.toyproject.demo.domain.sprint;

import com.toyproject.demo.domain.personalpage.ProjectDetail;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Diagram {

    @Id
    @Column(name = "diagram_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private ProjectDetail project;

    //다이어그램을 만들기 위한 이름
    @Column(nullable = true)
    private String superClassName;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Diagram() {
    }

    public Diagram(Long id) {
        this.id = id;
    }
}
