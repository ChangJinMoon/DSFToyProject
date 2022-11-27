package com.toyproject.demo.domain.code;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

// TODO: 2022/11/19 create Code Block Object method

@Entity
@Table(name = "code_block")
@Getter
public class CodeBlock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerId;

    private String code;

    private String text;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sprint_id")
    private Code codeGroup;

    public static CodeBlock createCodeBlock(Long writerId, String code, String text){
        CodeBlock temp = new CodeBlock();
        temp.writerId =  writerId;
        temp.code = code;
        temp.text = text;
        temp.createDate = LocalDateTime.now();
        return temp;
    }

    public void updateCodeBlock(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
