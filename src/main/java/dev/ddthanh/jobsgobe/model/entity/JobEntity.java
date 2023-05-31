package dev.ddthanh.jobsgobe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String required;
    @Column(columnDefinition = "text")
    private String benefit;
    private String city;
    private String districts;
    private String wards;
    private String specificAddress;
    private String phone;
    private String certificate;
    private String position;
    private String positionWork;
    private Integer gender;
    private String ageStart;
    private String ageEnd;
    private Double numberYearExperienceStart;
    private Double numberYearExperienceEnd;
    private String salaryFrom;
    private String salaryTo;
    private String natureOfWork;

    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id")
    private UserEntity recruiter; //for recruiter(user)

    @OneToMany(targetEntity = ApplyEntity.class, mappedBy = "job")
    private Set<ApplyEntity> listApply;//for apply

    @ManyToMany
    @JoinTable(
            name = "tbl_job_career",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "career_id"))
    private List<CareerEntity> listCareer;

    @ManyToMany
    @JoinTable(
            name = "tbl_job_pro_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "pro_skill_id"))
    private List<ProSkillEntity> listProSkill;

    @ManyToMany
    @JoinTable(
            name = "tbl_job_soft_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "soft_skill_id"))
    private List<SoftSkillEntity> listSoftSkill;

    @ManyToMany
    @JoinTable(
            name = "tbl_job_language",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<LanguageEntity> listLanguage;
}
