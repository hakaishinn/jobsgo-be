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

    public ResumeResponse getResumeResponse(ResumeEntity resume) {
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
                .status(resume.getStatus())
                .candidateId(resume.getCandidate().getId())
                .listResumeProSkill(resume.getListResumeProSkill().stream().map(proSkill ->
                        ResumeProSkillResponse.builder()
                                .id(proSkill.getId())
                                .yearExperience(proSkill.getYearExperience())
                                .proSkillId(proSkill.getProSkill() != null ? proSkill.getProSkill().getId() : null)
                                .proSkillName(proSkill.getProSkill() != null ? proSkill.getProSkill().getName() : proSkill.getName())
                                .build()
                ).collect(Collectors.toSet()))
                .listWorkExperience(resume.getListWorkExperience().stream().map(workExp ->
                        ResumeWorkExperienceEntity.builder()
                                .id(workExp.getId())
                                .nameCompany(workExp.getNameCompany())
                                .position(workExp.getPosition())
                                .startDay(workExp.getStartDay())
                                .endDay(workExp.getEndDay())
                                .statusWork(workExp.isStatusWork())
                                .description(workExp.getDescription())
                                .build()
                ).collect(Collectors.toSet()))
                .listResumeEducation(resume.getListResumeEducation().stream().map(education ->
                        ResumeEducationEntity.builder()
                                .id(education.getId())
                                .nameSchool(education.getNameSchool())
                                .majors(education.getMajors())
                                .degree(education.getDegree())
                                .graduationYear(education.getGraduationYear())
                                .statusEducation(education.isStatusEducation())
                                .description(education.getDescription())
                                .build()
                ).collect(Collectors.toSet()))
                .listResumeLanguage(resume.getListResumeLanguage().stream().map(language ->
                        ResumeLanguageResponse.builder()
                                .id(language.getId())
                                .prowess(language.getProwess())
                                .languageId(language.getLanguage() != null ? language.getLanguage().getId() : null)
                                .languageName(language.getLanguage() != null ? language.getLanguage().getName() : language.getName())
                                .build()
                ).collect(Collectors.toSet()))
                .listResumeSoftSkill(resume.getListResumeSoftSkill().stream().map(softSkill ->
                        ResumeSoftSkillResponse.builder()
                                .id(softSkill.getId())
                                .prowess(softSkill.getProwess())
                                .softSkillId(softSkill.getSoftSkill() != null ? softSkill.getSoftSkill().getId() : null)
                                .softSkillName(softSkill.getSoftSkill() != null ? softSkill.getSoftSkill().getName() : softSkill.getName())
                                .build()
                ).collect(Collectors.toSet()))
                .listResumeHobby(resume.getListResumeHobby().stream().map(hobby ->
                        ResumeHobbyEntity.builder()
                                .id(hobby.getId())
                                .name(hobby.getName())
                                .build()
                ).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Response<List<ResumeResponse>> showAll() {
        List<ResumeResponse> listResume = resumeRepository.findAll()
                .stream()
                .map(this::getResumeResponse)
                .collect(Collectors.toList());
        return Response.<List<ResumeResponse>>builder()
                .setData(listResume)
                .build();
    }

    @Override
    public Response<List<ResumeResponse>> showAllByCandidateId(Long id) {
        List<ResumeResponse> listResume = resumeRepository.showAllByCandidateId(id)
                .stream()
                .map(this::getResumeResponse)
                .collect(Collectors.toList());
        return Response.<List<ResumeResponse>>builder()
                .setData(listResume)
                .build();
    }

    @Override
    public Response<ResumeResponse> showOneResume(Long id) {
        ResumeEntity resume = resumeRepository.findById(id).orElse(null);
        if (resume == null) {
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
        UserEntity candidate = userRepository.findById(request.getCandidateId()).orElse(null);
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
                .status(request.getStatus())
                .candidate(candidate)
                .listResumeHobby(new HashSet<>())
                .listResumeProSkill(new HashSet<>())
                .listWorkExperience(new HashSet<>())
                .listResumeEducation(new HashSet<>())
                .listResumeLanguage(new HashSet<>())
                .listResumeSoftSkill(new HashSet<>())
                .build();
        //ProSkill
        for (ResumeProSkillRequest proSkill : request.getListResumeProSkill()) {
            ResumeProSkillEntity resumeProSkillEntity = new ResumeProSkillEntity();
            resumeProSkillEntity.setYearExperience(proSkill.getYearExperience());
            resumeProSkillEntity.setResume(resume);
            resumeProSkillEntity.setName(proSkill.getProSkillName());
            if (proSkill.getProSkillId() != null) {
                ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkill.getProSkillId()).orElse(null);
                if (proSkillEntity != null) {
                    resumeProSkillEntity.setProSkill(proSkillEntity);
                }
            }
            resume.getListResumeProSkill().add(resumeProSkillEntity);
        }
        //Language
        for (ResumeLanguageRequest resumeLanguageRequest : request.getListResumeLanguage()) {
            ResumeLanguageEntity resumeLanguageEntity = new ResumeLanguageEntity();
            resumeLanguageEntity.setProwess(resumeLanguageRequest.getProwess());
            resumeLanguageEntity.setName(resumeLanguageRequest.getLanguageName());
            if (resumeLanguageRequest.getLanguageId() != null) {
                LanguageEntity languageEntity = languageRepository.findById(resumeLanguageRequest.getLanguageId()).orElse(null);
                if (languageEntity != null) {
                    resumeLanguageEntity.setLanguage(languageEntity);
                }
            }
            resumeLanguageEntity.setResume(resume);
            resume.getListResumeLanguage().add(resumeLanguageEntity);
        }
        //SoftSkill
        for (ResumeSoftSkillRequest resumeSoftSkillRequest : request.getListResumeSoftSkill()) {
            ResumeSoftSkillEntity resumeSoftSkillEntity = new ResumeSoftSkillEntity();
            resumeSoftSkillEntity.setProwess(resumeSoftSkillRequest.getProwess());
            resumeSoftSkillEntity.setName(resumeSoftSkillRequest.getSoftSkillName());
            if (resumeSoftSkillRequest.getSoftSkillId() != null) {
                SoftSkillEntity softSkillEntity = softSkillRepository.findById(resumeSoftSkillRequest.getSoftSkillId()).orElse(null);
                if (softSkillEntity != null) {
                    resumeSoftSkillEntity.setSoftSkill(softSkillEntity);
                }
            }
            resumeSoftSkillEntity.setResume(resume);
            resume.getListResumeSoftSkill().add(resumeSoftSkillEntity);
        }
        //Exp
        for (ResumeWorkExperienceEntity exp : request.getListWorkExperience()) {
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
        for (ResumeEducationEntity educationReq : request.getListResumeEducation()) {
            ResumeEducationEntity resumeEducationEntity = new ResumeEducationEntity();
            resumeEducationEntity.setNameSchool(educationReq.getNameSchool());
            resumeEducationEntity.setMajors(educationReq.getMajors());
            resumeEducationEntity.setDegree(educationReq.getDegree());
            resumeEducationEntity.setGraduationYear(educationReq.getGraduationYear());
            resumeEducationEntity.setStatusEducation(educationReq.isStatusEducation());
            resumeEducationEntity.setDescription(educationReq.getDescription());
            resumeEducationEntity.setResume(resume);
            resume.getListResumeEducation().add(resumeEducationEntity);
        }
        //Hobby
        for (ResumeHobbyEntity hobby : request.getListResumeHobby()) {
            ResumeHobbyEntity hobbyEntity = new ResumeHobbyEntity();
            hobbyEntity.setName(hobby.getName());
            hobbyEntity.setResume(resume);
            resume.getListResumeHobby().add(hobbyEntity);
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
        if (resumeOld == null) {
            return Response.<ResumeResponse>builder()
                    .setMessage("Not found")
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .setSuccess(false)
                    .setStatusCode(404)
                    .build();
        }
        UserEntity candidate = userRepository.findById(request.getCandidateId()).orElse(null);
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
                .status(request.getStatus())
                .candidate(candidate)
                .listResumeHobby(new HashSet<>())
                .listResumeProSkill(new HashSet<>())
                .listWorkExperience(new HashSet<>())
                .listResumeEducation(new HashSet<>())
                .listResumeLanguage(new HashSet<>())
                .listResumeSoftSkill(new HashSet<>())
                .build();

        //reset list
        for (ResumeHobbyEntity hobbyEntity : resumeOld.getListResumeHobby()) {
            hobbyEntity.setResume(null);
            resumeHobbyRepository.save(hobbyEntity);
        }

        for (ResumeProSkillEntity resumeProSkillEntity : resumeOld.getListResumeProSkill()) {
            resumeProSkillEntity.setResume(null);
            resumeProSkillRepository.save(resumeProSkillEntity);
        }

        for (ResumeWorkExperienceEntity workExperienceEntity : resumeOld.getListWorkExperience()) {
            workExperienceEntity.setResume(null);
            resumeWorkExpRepository.save(workExperienceEntity);
        }

        for (ResumeEducationEntity resumeEducationEntity : resumeOld.getListResumeEducation()) {
            resumeEducationEntity.setResume(null);
            resumeEducationRepository.save(resumeEducationEntity);
        }

        for (ResumeLanguageEntity resumeLanguageEntity : resumeOld.getListResumeLanguage()) {
            resumeLanguageEntity.setResume(null);
            resumeLanguageRepository.save(resumeLanguageEntity);
        }

        for (ResumeSoftSkillEntity resumeSoftSkillEntity : resumeOld.getListResumeSoftSkill()) {
            resumeSoftSkillEntity.setResume(null);
            resumeSoftSkillRepository.save(resumeSoftSkillEntity);
        }
        //end reset
        //ProSkill
        for (ResumeProSkillRequest proSkillRequest : request.getListResumeProSkill()) {
            ResumeProSkillEntity resumeProSkillEntity = new ResumeProSkillEntity();
            if (proSkillRequest.getId() != null) {
                resumeProSkillEntity.setId(proSkillRequest.getId());
            }
            resumeProSkillEntity.setYearExperience(proSkillRequest.getYearExperience());
            resumeProSkillEntity.setName(proSkillRequest.getProSkillName());
            resumeProSkillEntity.setResume(resume);
            if(proSkillRequest.getProSkillId() != null){
                ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkillRequest.getProSkillId()).orElse(null);
                resumeProSkillEntity.setProSkill(proSkillEntity);
            }
            resume.getListResumeProSkill().add(resumeProSkillEntity);
        }
        //Language
        for (ResumeLanguageRequest resumeLanguageRequest : request.getListResumeLanguage()) {
            ResumeLanguageEntity resumeLanguageEntity = new ResumeLanguageEntity();
            if (resumeLanguageRequest.getId() != null) {
                resumeLanguageEntity.setId(resumeLanguageRequest.getId());
            }
            resumeLanguageEntity.setProwess(resumeLanguageRequest.getProwess());
            resumeLanguageEntity.setName(resumeLanguageRequest.getLanguageName());
            if(resumeLanguageRequest.getLanguageId() != null){
                LanguageEntity languageEntity = languageRepository.findById(resumeLanguageRequest.getLanguageId()).orElse(null);
                resumeLanguageEntity.setLanguage(languageEntity);
            }
            resumeLanguageEntity.setResume(resume);
            resume.getListResumeLanguage().add(resumeLanguageEntity);
        }
        //SoftSkill
        for (ResumeSoftSkillRequest resumeSoftSkillRequest : request.getListResumeSoftSkill()) {
            ResumeSoftSkillEntity resumeSoftSkillEntity = new ResumeSoftSkillEntity();
            if (resumeSoftSkillRequest.getId() != null) {
                resumeSoftSkillEntity.setId(resumeSoftSkillRequest.getId());
            }
            resumeSoftSkillEntity.setProwess(resumeSoftSkillRequest.getProwess());
            resumeSoftSkillEntity.setName(resumeSoftSkillEntity.getName());
            if(resumeSoftSkillRequest.getSoftSkillId() != null){
                SoftSkillEntity softSkillEntity = softSkillRepository.findById(resumeSoftSkillRequest.getSoftSkillId()).orElse(null);
                resumeSoftSkillEntity.setSoftSkill(softSkillEntity);
            }
            resumeSoftSkillEntity.setResume(resume);
            resume.getListResumeSoftSkill().add(resumeSoftSkillEntity);
        }
        //Exp
        for (ResumeWorkExperienceEntity exp : request.getListWorkExperience()) {
            ResumeWorkExperienceEntity resumeExp = new ResumeWorkExperienceEntity();
            if (exp.getId() != null) {
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
        for (ResumeEducationEntity educationReq : request.getListResumeEducation()) {
            ResumeEducationEntity resumeEducationEntity = new ResumeEducationEntity();
            if (educationReq.getId() != null) {
                resumeEducationEntity.setId(educationReq.getId());
            }
            resumeEducationEntity.setNameSchool(educationReq.getNameSchool());
            resumeEducationEntity.setMajors(educationReq.getMajors());
            resumeEducationEntity.setDegree(educationReq.getDegree());
            resumeEducationEntity.setGraduationYear(educationReq.getGraduationYear());
            resumeEducationEntity.setDescription(educationReq.getDescription());
            resumeEducationEntity.setStatusEducation(resumeEducationEntity.isStatusEducation());
            resumeEducationEntity.setResume(resume);
            resume.getListResumeEducation().add(resumeEducationEntity);
        }
        //Hobby
        for (ResumeHobbyEntity hobby : request.getListResumeHobby()) {
            ResumeHobbyEntity hobbyEntity = new ResumeHobbyEntity();
            if (hobby.getId() != null) {
                hobbyEntity.setId(hobby.getId());
            }
            hobbyEntity.setResume(resume);
            hobbyEntity.setName(hobby.getName());
            resume.getListResumeHobby().add(hobbyEntity);
        }
        resumeRepository.save(resume);
        resumeProSkillRepository.deleteResumeIdNull();
        resumeWorkExpRepository.deleteResumeIdNull();
        resumeEducationRepository.deleteResumeIdNull();
        resumeLanguageRepository.deleteResumeIdNull();
        resumeSoftSkillRepository.deleteResumeIdNull();
        resumeHobbyRepository.deleteResumeIdNull();
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
