package com.toyproject.demo.domain.code;

import javax.persistence.*;

// TODO: 2022/11/19 create Code Block Object method

@Entity
@Table(name = "code_block")
public class CodeBlock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sprint_id")
    private Code codeGroup;
}
