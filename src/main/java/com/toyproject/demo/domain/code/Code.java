package com.toyproject.demo.domain.code;


import com.toyproject.demo.domain.sprint.Sprint;
import com.toyproject.demo.dto.code.CodeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    Long id;

    @Column
    String title;

    @Column
    String context;

    @Column
    Long writerId;

    @Column
    LocalDateTime writeDate;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Sprint sprint;

    public Code (CodeDto codeDto){
        title = codeDto.getTitle();
        context = codeDto.getContext();
        writerId = codeDto.getWriterId();
        writeDate = LocalDateTime.now();
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Code() {
    }

}
