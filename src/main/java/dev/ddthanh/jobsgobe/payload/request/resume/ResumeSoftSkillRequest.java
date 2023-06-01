package dev.ddthanh.jobsgobe.payload.request.resume;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeSoftSkillRequest {
    private Integer prowess;
    private Long softSkillId;
}
