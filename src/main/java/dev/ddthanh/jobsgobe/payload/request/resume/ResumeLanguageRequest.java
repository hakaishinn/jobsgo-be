package dev.ddthanh.jobsgobe.payload.request.resume;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeLanguageRequest {
    private Integer prowess;
    private Long languageId;
}
