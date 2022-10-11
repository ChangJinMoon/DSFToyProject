package com.toyproject.demo.dto.code;

import com.toyproject.demo.domain.code.Code;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
public class CodeUpdateDto {
    private String title;
    private String context;
    private LocalDateTime localDateTime;

    public Code DtoToEntity(){
        Code code = new Code();
        code.setTitle(title);
        code.setContext(context);
        code.setWriteDate(LocalDateTime.now());

        return code;
    }
}
