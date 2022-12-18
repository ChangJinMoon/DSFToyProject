package com.toyproject.demo.dto.personalpage.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor

public class PersonalPageInitRequestDto {


    private Long userId;

    @Builder
    public PersonalPageInitRequestDto(@NonNull Long userId) {
        this.userId = userId;
    }
}
