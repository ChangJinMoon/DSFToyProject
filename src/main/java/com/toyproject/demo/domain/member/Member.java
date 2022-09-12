package com.toyproject.demo.domain.member;

import com.toyproject.demo.domain.Project;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id" )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String findPasswordAnswer;


    // 다대다 관계
    @OneToMany(mappedBy = "member_id")
    @JoinColumn(name = "project_id")
    private List<Project> projects;


}
