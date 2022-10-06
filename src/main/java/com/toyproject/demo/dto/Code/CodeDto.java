package com.toyproject.demo.dto.Code;

import com.toyproject.demo.domain.code.Code;
import com.toyproject.demo.domain.sprint.Sprint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CodeDto {

    String title;
    String context;
    Long writerId;


    public static Code DtoToEntity(CodeDto codeDto){
        Code code = new Code(codeDto);
        return code;
    }

}
