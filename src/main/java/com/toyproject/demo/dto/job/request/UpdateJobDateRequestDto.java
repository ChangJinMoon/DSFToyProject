package com.toyproject.demo.dto.job.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateJobDateRequestDto {

    private Long jobId;

    private LocalDateTime start;

    private LocalDateTime end;

    public UpdateJobDateRequestDto(@NonNull Long jobId,@NonNull LocalDateTime start,@NonNull LocalDateTime end) {
        this.jobId = jobId;
        this.start = start;
        this.end = end;
    }
}
