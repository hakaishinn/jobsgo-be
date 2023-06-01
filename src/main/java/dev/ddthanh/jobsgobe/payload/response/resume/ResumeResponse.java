package dev.ddthanh.jobsgobe.payload.response.resume;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResumeResponse {
    private Long resumeId;
    private Long candidateId;
}
