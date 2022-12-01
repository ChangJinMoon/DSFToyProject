package com.toyproject.demo.dto.job.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class UpdateJobDateRequestDto {

    @NotNull
    private Long jobId;

    @NotNull
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date start;

    @NotNull
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date end;

    public UpdateJobDateRequestDto(@NonNull Long jobId,@NonNull Date start,@NonNull Date end) {
        this.jobId = jobId;
        this.start = start;
        this.end = end;
    }
}
