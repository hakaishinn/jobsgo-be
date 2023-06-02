package dev.ddthanh.jobsgobe.payload.response.resume;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeProSkillResponse {
    private Long id;
    private Double yearExperience;
    private Long proSkillId;
    private String proSkillName;
}
