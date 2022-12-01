package com.toyproject.demo.domain.code;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_block")
@Getter
public class CodeBlock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_block_id")
    private Long id;

    private Long writerId;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private Code codeGroup;

    public static CodeBlock createCodeBlock(Long writerId, String code, String text,Code codeGroup){
        CodeBlock temp = new CodeBlock();
        temp.writerId =  writerId;
        temp.code = code;
        temp.text = text;
        temp.createDate = LocalDateTime.now();
        temp.codeGroup = codeGroup;
        return temp;
    }

    public void updateCodeBlock(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public void deleteCode(){
        this.codeGroup = null;
    }

    @Override
    public String toString() {
        return "CodeBlock{" +
                "id=" + id +
                ", writerId=" + writerId +
                ", code='" + code + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", codeGroup=" + codeGroup +
                '}';
    }
}
