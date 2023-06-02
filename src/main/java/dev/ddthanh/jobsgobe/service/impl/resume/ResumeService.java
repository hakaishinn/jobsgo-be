package dev.ddthanh.jobsgobe.service.impl.resume;

import dev.ddthanh.jobsgobe.model.entity.*;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeLanguageRequest;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeProSkillRequest;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeRequest;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeSoftSkillRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeLanguageResponse;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeProSkillResponse;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeSoftSkillResponse;
import dev.ddthanh.jobsgobe.repository.language.LanguageRepository;
import dev.ddthanh.jobsgobe.repository.proSkill.ProSkillRepository;
import dev.ddthanh.jobsgobe.repository.resume.*;
import dev.ddthanh.jobsgobe.repository.softSkill.SoftSkillRepository;
import dev.ddthanh.jobsgobe.repository.user.UserRepository;
import dev.ddthanh.jobsgobe.service.iservice.ResumeIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService implements ResumeIService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final ProSkillRepository proSkillRepository;
    private final LanguageRepository languageRepository;
    private final SoftSkillRepository softSkillRepository;
    private final ResumeProSkillRepository resumeProSkillRepository;
    private final ResumeWorkExpRepository resumeWorkExpRepository;
    private final ResumeLanguageRepository resumeLanguageRepository;
    private final ResumeSoftSkillRepository resumeSoftSkillRepository;
    private final ResumeHobbyRepository resumeHobbyRepository;
    private final ResumeEducationRepository resumeEducationRepository;

    public ResumeResponse getResumeResponse(ResumeEntity resume){
        return ResumeResponse.builder()
                .resumeId(resume.getId())
                .name(resume.getName())
                .image(resume.getImage())
                .birthday(resume.getBirthday())
                .typePosition(resume.getTypePosition())
                .positionApply(resume.getPositionApply())
                .phone(resume.getPhone())
                .email(resume.getEmail())
                .address(resume.getAddress())
                .currentSalary(resume.getCurrentSalary())
                .desiredSalary(resume.getDesiredSalary())
                .introduce(resume.getIntroduce())
                .careerGoals(resume.getCareerGoals())
                .createAt(resume.getCreateAt())
                .updateAt(resume.getUpdateAt())
                .status(resume.isStatus())
                .candidateId(resume.getCandidate().getId())
                .listResumeProSkill(resume.getListResumeProSkill().stream().map(proSkill -> {
                    return ResumeProSkillResponse.builder()
                            .id(proSkill.getId())
                            .yearExperience(proSkill.getYearExperience())
                            .proSkillId(proSkill.getProSkill().getId())
                            .proSkillName(proSkill.getProSkill().getName())
                            .build();
                }).collect(Collectors.toSet()))
                .listWorkExperience(resume.getListWorkExperience().stream().map(workExp -> {
                    return ResumeWorkExperienceEntity.builder()
                            .id(workExp.getId())
                            .nameCompany(workExp.getNameCompany())
                            .position(workExp.getPosition())
                            .startDay(workExp.getStartDay())
                            .endDay(workExp.getEndDay())
                            .statusWork(workExp.isStatusWork())
                            .description(workExp.getDescription())
                            .build();
                }).collect(Collectors.toSet()))
                .listResumeEducation(resume.getListResumeEducation().stream().map(education -> {
                    return ResumeEducationEntity.builder()
                            .id(education.getId())
                            .nameSchool(education.getNameSchool())
                            .majors(education.getMajors())
                            .certificate(education.getCertificate())
                            .graduationYear(education.getGraduationYear())
                            .description(education.getDescription())
                            .build();
                }).collect(Collectors.toSet()))
                .listResumeLanguage(resume.getListResumeLanguage().stream().map(language -> {
                    return ResumeLanguageResponse.builder()
                            .id(language.getId())
                            .prowess(language.getProwess())
                            .languageId(language.getLanguage().getId())
                            .languageName(language.getLanguage().getName())
                            .build();
                }).collect(Collectors.toSet()))
                .listResumeSoftSkill(resume.getListResumeSoftSkill().stream().map(softSkill -> {
                    return ResumeSoftSkillResponse.builder()
                            .id(softSkill.getId())
                            .prowess(softSkill.getProwess())
                            .softSkillId(softSkill.getSoftSkill().getId())
                            .softSkillName(softSkill.getSoftSkill().getName())
                            .build();
                }).collect(Collectors.toSet()))
                .listResumeHobby(resume.getListResumeHobby().stream().map(hobby -> {
                    return ResumeHobbyEntity.builder()
                            .id(hobby.getId())
                            .name(hobby.getName())
                            .build();
                }).collect(Collectors.toSet()))
                .build();
    }
    @Override
    public Response<List<ResumeResponse>> showAll() {
        List<ResumeResponse> listResume = resumeRepository.findAll().stream().map(resume -> {
            return getResumeResponse(resume);
        }).collect(Collectors.toList());
        return Response.<List<ResumeResponse>>builder()
                .setData(listResume)
                .build();
    }

    @Override
    public Response<ResumeResponse> showOneResume(Long id) {
        ResumeEntity resume = resumeRepository.findById(id).orElse(null);
        if(resume == null) {
            return Response.<ResumeResponse>builder()
                    .setSuccess(false)
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .setStatusCode(404)
                    .setMessage("Not found")
                    .build();
        }
        return Response.<ResumeResponse>builder()
                .setData(getResumeResponse(resume))
                .build();
    }

    @Override
    public Response<ResumeResponse> create(ResumeRequest request) {
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
                .createAt(new Date())
                .status(request.isStatus())
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
        return Response.<ResumeResponse>builder()
                .setMessage("Create resume success")
                .setStatusCode(200)
                .setStatus(HttpStatus.CREATED)
                .setData(getResumeResponse(resume))
                .build();
    }
    @Override
    public Response<ResumeResponse> update(Long id, ResumeRequest request) {
        ResumeEntity resumeOld = resumeRepository.findById(id).orElse(null);
        if(resumeOld == null){
            return Response.<ResumeResponse>builder()
                .setMessage("Not found")
                .setStatus(HttpStatus.BAD_REQUEST)
                .setSuccess(false)
                .setStatusCode(404)
                .build();
        }
        Optional<UserEntity> candidate = userRepository.findById(request.getCandidateId());
        ResumeEntity resume = ResumeEntity.builder()
                .id(id)
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
                .updateAt(new Date())
                .status(request.isStatus())
                .candidate(candidate.get())
                .listResumeHobby(new HashSet<>())
                .listResumeProSkill(new HashSet<>())
                .listWorkExperience(new HashSet<>())
                .listResumeEducation(new HashSet<>())
                .listResumeLanguage(new HashSet<>())
                .listResumeSoftSkill(new HashSet<>())
                .build();
        //ProSkill
        for (ResumeProSkillRequest proSkillRequest: request.getListResumeProSkill()) {
            ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkillRequest.getProSkillId()).get();
            ResumeProSkillEntity resumeProSkillEntity = new ResumeProSkillEntity();
            if(proSkillRequest.getId() != null){
                resumeProSkillEntity.setId(proSkillRequest.getId());
            }
            resumeProSkillEntity.setYearExperience(proSkillRequest.getYearExperience());
            resumeProSkillEntity.setResume(resume);
            resumeProSkillEntity.setProSkill(proSkillEntity);
            resume.getListResumeProSkill().add(resumeProSkillEntity);
        }
        //Language
        for (ResumeLanguageRequest resumeLanguageRequest: request.getListResumeLanguage()) {
            LanguageEntity languageEntity = languageRepository.findById(resumeLanguageRequest.getLanguageId()).get();
            ResumeLanguageEntity resumeLanguageEntity = new ResumeLanguageEntity();
            if(resumeLanguageRequest.getId() != null){
                resumeLanguageEntity.setId(resumeLanguageRequest.getId());
            }
            resumeLanguageEntity.setProwess(resumeLanguageRequest.getProwess());
            resumeLanguageEntity.setLanguage(languageEntity);
            resumeLanguageEntity.setResume(resume);
            resume.getListResumeLanguage().add(resumeLanguageEntity);
        }
        //SoftSkill
        for (ResumeSoftSkillRequest resumeSoftSkillRequest: request.getListResumeSoftSkill()) {
            SoftSkillEntity softSkillEntity = softSkillRepository.findById(resumeSoftSkillRequest.getSoftSkillId()).get();
            ResumeSoftSkillEntity resumeSoftSkillEntity = new ResumeSoftSkillEntity();
            if(resumeSoftSkillRequest.getId() != null){
                resumeSoftSkillEntity.setId(resumeSoftSkillRequest.getId());
            }
            resumeSoftSkillEntity.setProwess(resumeSoftSkillRequest.getProwess());
            resumeSoftSkillEntity.setSoftSkill(softSkillEntity);
            resumeSoftSkillEntity.setResume(resume);
            resume.getListResumeSoftSkill().add(resumeSoftSkillEntity);
        }
        //Exp
        for (ResumeWorkExperienceEntity exp: request.getListWorkExperience()) {
            ResumeWorkExperienceEntity resumeExp = new ResumeWorkExperienceEntity();
            if(exp.getId() != null){
                resumeExp.setId(exp.getId());
            }
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
            if(educationReq.getId() != null){
                resumeEducationEntity.setId(educationReq.getId());
            }
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
            if(hobby.getId() != null){
                hobbyCurrent.setId(hobby.getId());
            }
            resume.getListResumeHobby().add(hobbyCurrent);
        }
        resume.getListResumeHobby().clear();
        resumeRepository.save(resume);
        return Response.<ResumeResponse>builder()
                .setMessage("Update resume success")
                .setData(getResumeResponse(resume))
                .build();
    }

    @Override
    public void deleteResumeProSkillById(Long id) {
        resumeProSkillRepository.deleteById(id);
    }

    @Override
    public void deleteResumeWorkExpById(Long id) {
        resumeWorkExpRepository.deleteById(id);
    }

    @Override
    public void deleteResumeEducationById(Long id) {
        resumeEducationRepository.deleteById(id);
    }

    @Override
    public void deleteResumeLanguageById(Long id) {
        resumeLanguageRepository.deleteById(id);
    }

    @Override
    public void deleteResumeSoftSkillById(Long id) {
        resumeSoftSkillRepository.deleteById(id);
    }

    @Override
    public void deleteResumeHobbyById(Long id) {
        resumeHobbyRepository.deleteById(id);
    }

    @Override
    public Response<ResumeResponse> delete(Long id) {
        resumeRepository.deleteById(id);
        return Response.<ResumeResponse>builder()
                .setMessage("Delete resume success")
                .setData(ResumeResponse.builder()
                        .resumeId(id)
                        .build())
                .build();
    }
}
