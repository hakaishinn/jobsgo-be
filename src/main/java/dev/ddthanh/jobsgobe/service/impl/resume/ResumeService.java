package dev.ddthanh.jobsgobe.service.impl.resume;

import dev.ddthanh.jobsgobe.model.entity.*;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeLanguageRequest;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeProSkillRequest;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeRequest;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeSoftSkillRequest;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import dev.ddthanh.jobsgobe.repository.resume.LanguageRepository;
import dev.ddthanh.jobsgobe.repository.resume.ProSkillRepository;
import dev.ddthanh.jobsgobe.repository.resume.ResumeRepository;
import dev.ddthanh.jobsgobe.repository.resume.SoftSkillRepository;
import dev.ddthanh.jobsgobe.repository.user.UserRepository;
import dev.ddthanh.jobsgobe.service.iservice.ResumeIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService implements ResumeIService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final ProSkillRepository proSkillRepository;
    private final LanguageRepository languageRepository;
    private final SoftSkillRepository softSkillRepository;
    @Override
    public ResumeResponse create(ResumeRequest request) {
        Optional<UserEntity> candidate = userRepository.findById(request.getCandidateId());
        ResumeEntity resume = ResumeEntity.builder()
                .name(request.getName())
                .image(request.getImage())
                .birthday(request.getBirthday())
                .typePosition(request.getTypePosition())
                .positionApply(request.getPositionApply())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .currentSalary(request.getCurrentSalary())
                .desiredSalary(request.getDesiredSalary())
                .introduce(request.getIntroduce())
                .careerGoals(request.getCareerGoals())
                .candidate(candidate.get())
                .listResumeHobby(new HashSet<>())
                .listResumeProSkill(new HashSet<>())
                .listWorkExperience(new HashSet<>())
                .listResumeEducation(new HashSet<>())
                .listResumeLanguage(new HashSet<>())
                .listResumeSoftSkill(new HashSet<>())
                .build();
        //ProSkill
        for (ResumeProSkillRequest proSkill: request.getListResumeProSkill()) {
            ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkill.getProSkillId()).get();
            ResumeProSkillEntity resumeProSkillEntity = new ResumeProSkillEntity();
            resumeProSkillEntity.setYearExperience(proSkill.getYearExperience());
            resumeProSkillEntity.setResume(resume);
            resumeProSkillEntity.setProSkill(proSkillEntity);
            resume.getListResumeProSkill().add(resumeProSkillEntity);
        }
        //Language
        for (ResumeLanguageRequest resumeLanguageRequest: request.getListResumeLanguage()) {
            LanguageEntity languageEntity = languageRepository.findById(resumeLanguageRequest.getLanguageId()).get();
            ResumeLanguageEntity resumeLanguageEntity = new ResumeLanguageEntity();
            resumeLanguageEntity.setProwess(resumeLanguageRequest.getProwess());
            resumeLanguageEntity.setLanguage(languageEntity);
            resumeLanguageEntity.setResume(resume);
            resume.getListResumeLanguage().add(resumeLanguageEntity);
        }
        //SoftSkill
        for (ResumeSoftSkillRequest resumeSoftSkillRequest: request.getListResumeSoftSkill()) {
            SoftSkillEntity softSkillEntity = softSkillRepository.findById(resumeSoftSkillRequest.getSoftSkillId()).get();
            ResumeSoftSkillEntity resumeSoftSkillEntity = new ResumeSoftSkillEntity();
            resumeSoftSkillEntity.setProwess(resumeSoftSkillRequest.getProwess());
            resumeSoftSkillEntity.setSoftSkill(softSkillEntity);
            resumeSoftSkillEntity.setResume(resume);
            resume.getListResumeSoftSkill().add(resumeSoftSkillEntity);
        }
        //Exp
        for (ResumeWorkExperienceEntity exp: request.getListWorkExperience()) {
           ResumeWorkExperienceEntity resumeExp = new ResumeWorkExperienceEntity();
           resumeExp.setNameCompany(exp.getNameCompany());
           resumeExp.setPosition(exp.getPosition());
           resumeExp.setStartDay(exp.getStartDay());
           resumeExp.setEndDay(exp.getEndDay());
           resumeExp.setStatusWork(exp.isStatusWork());
           resumeExp.setDescription(exp.getDescription());
           resumeExp.setResume(resume);
           resume.getListWorkExperience().add(resumeExp);
        }
        //Education
        for (ResumeEducationEntity educationReq: request.getListResumeEducation()) {
            ResumeEducationEntity resumeEducationEntity = new ResumeEducationEntity();
            resumeEducationEntity.setNameSchool(educationReq.getNameSchool());
            resumeEducationEntity.setMajors(educationReq.getMajors());
            resumeEducationEntity.setCertificate(educationReq.getCertificate());
            resumeEducationEntity.setGraduationYear(educationReq.getGraduationYear());
            resumeEducationEntity.setDescription(educationReq.getDescription());
            resumeEducationEntity.setResume(resume);
            resume.getListResumeEducation().add(resumeEducationEntity);
        }
        //Hobby
        for (ResumeHobbyEntity hobby: request.getListResumeHobby()) {
            ResumeHobbyEntity hobbyCurrent = new ResumeHobbyEntity(hobby.getName(), resume);
            resume.getListResumeHobby().add(hobbyCurrent);
        }
        resumeRepository.save(resume);
        return ResumeResponse.builder()
                .resumeId(resume.getId())
                .candidateId(resume.getCandidate().getId())
                .build();
    }
}
