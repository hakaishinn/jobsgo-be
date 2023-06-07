package dev.ddthanh.jobsgobe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private String district;
    private String ward;
    private String specificAddress;
    private String phone;
    private String degree;
    private String typePosition;
    private Integer gender;
    private Double ageStart;
    private Double ageEnd;
    private Double numberYearExperienceStart;
    private Double numberYearExperienceEnd;
    private Double salaryFrom;
    private Double salaryTo;
    private String natureOfWork;
    private Integer status;
    private Date createAt;
    private Date updateAt;

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
    private List<CareerEntity> listCareer; //for career

    @ManyToMany
    @JoinTable(
            name = "tbl_job_pro_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "pro_skill_id"))
    private List<ProSkillEntity> listProSkill; //for pro skill

    @ManyToMany
    @JoinTable(
            name = "tbl_job_soft_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "soft_skill_id"))
    private List<SoftSkillEntity> listSoftSkill; //for soft skill

    @ManyToMany
    @JoinTable(
            name = "tbl_job_language",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<LanguageEntity> listLanguage; //for language
}
