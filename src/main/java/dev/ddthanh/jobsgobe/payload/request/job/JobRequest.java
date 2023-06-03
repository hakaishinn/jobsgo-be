package dev.ddthanh.jobsgobe.payload.request.job;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class JobRequest {
    private String title;
    private String description;
    private String required;
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
    private Integer ageStart;
    private Integer ageEnd;
    private Double numberYearExperienceStart;
    private Double numberYearExperienceEnd;
    private Double salaryFrom;
    private Double salaryTo;
    private String natureOfWork;
    private List<CareerRequest> listCareer;
    private List<ProSkillRequest> listProSkill;
    private List<SoftSkillRequest> listSoftSkill;
    private List<LanguageRequest> listLanguage;
}
