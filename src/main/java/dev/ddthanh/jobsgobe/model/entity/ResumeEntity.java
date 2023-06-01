package dev.ddthanh.jobsgobe.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_resume")
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private Date birthday;
    private String typePosition;
    private String positionApply;
    private String phone;
    private String email;
    private String address;
    private String currentSalary;
    private String desiredSalary;
    @Column(columnDefinition = "text")
    private String introduce;
    @Column(columnDefinition = "text")
    private String careerGoals;

    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private UserEntity candidate; //for candidate(user)

    @OneToMany(targetEntity = ApplyEntity.class, mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ApplyEntity> listApply;//for apply

    @OneToMany(targetEntity = ResumeProSkillEntity.class, mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeProSkillEntity> listResumeProSkill;//for resume proSkill

    @OneToMany(targetEntity = ResumeWorkExperienceEntity.class, mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeWorkExperienceEntity> listWorkExperience;//for resume exp

    @OneToMany(targetEntity = ResumeEducationEntity.class, mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeEducationEntity> listResumeEducation;//for resume education

    @OneToMany(targetEntity = ResumeLanguageEntity.class, mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeLanguageEntity> listResumeLanguage;//for resume language

    @OneToMany(targetEntity = ResumeSoftSkillEntity.class, mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeSoftSkillEntity> listResumeSoftSkill;//for resume soft skill

    @OneToMany(targetEntity = ResumeHobbyEntity.class,mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeHobbyEntity> listResumeHobby;//for resume hobby
}
