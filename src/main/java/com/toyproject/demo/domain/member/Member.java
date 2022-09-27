package com.toyproject.demo.domain.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toyproject.demo.domain.MemberProject;
import com.toyproject.demo.domain.personalpage.ProjectDetail;
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
    @OneToMany(mappedBy = "member")
    //@JoinColumn(name = "project_id")
    private List<MemberProject> projects;


}
