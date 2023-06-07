package dev.ddthanh.jobsgobe.payload.response.job;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String required;
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
    private Date createAt;
    private Date updateAt;
    private Integer status;
    private List<CareerEntity> listCareer;
    private List<ProSkillEntity> listProSkill;
    private List<SoftSkillEntity> listSoftSkill;
    private List<LanguageEntity> listLanguage;
}
