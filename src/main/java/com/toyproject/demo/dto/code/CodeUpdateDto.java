package com.toyproject.demo.dto.code;

import com.toyproject.demo.domain.code.Code;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class CodeUpdateDto {
    private String title;
    private String context;
    private LocalDateTime localDateTime;

    public CodeUpdateDto(@NonNull String title, String context){
        this.title = title;
        this.context = context;
        this.localDateTime = LocalDateTime.now();
    }


    public Code DtoToEntity(@NonNull String title, String context){
        Code code = new Code();

        return code;
    }
}
