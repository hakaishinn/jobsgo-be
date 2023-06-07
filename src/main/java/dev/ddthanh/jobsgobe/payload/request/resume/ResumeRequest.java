package dev.ddthanh.jobsgobe.payload.request.resume;

import dev.ddthanh.jobsgobe.model.entity.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeRequest {
    private String name;
    private String image;
    private Date birthday;
    private String typePosition;
    private String positionApply;
    private String phone;
    private String email;
    private String address;
    private Double currentSalary;
    private Double desiredSalary;
    private String introduce;
    private String careerGoals;
    private Long candidateId;
    private Integer status;
    private Set<ResumeProSkillRequest> listResumeProSkill;
    private Set<ResumeWorkExperienceEntity> listWorkExperience;
    private Set<ResumeEducationEntity> listResumeEducation;
    private Set<ResumeLanguageRequest> listResumeLanguage;
    private Set<ResumeSoftSkillRequest> listResumeSoftSkill;
    private Set<ResumeHobbyEntity> listResumeHobby;
}
