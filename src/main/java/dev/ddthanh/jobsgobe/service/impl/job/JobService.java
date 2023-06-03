package dev.ddthanh.jobsgobe.service.impl.job;

import dev.ddthanh.jobsgobe.model.entity.*;
import dev.ddthanh.jobsgobe.payload.request.job.*;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.job.JobResponse;
import dev.ddthanh.jobsgobe.repository.career.CareerRepository;
import dev.ddthanh.jobsgobe.repository.job.JobRepository;
import dev.ddthanh.jobsgobe.repository.language.LanguageRepository;
import dev.ddthanh.jobsgobe.repository.proSkill.ProSkillRepository;
import dev.ddthanh.jobsgobe.repository.resume.ResumeProSkillRepository;
import dev.ddthanh.jobsgobe.repository.softSkill.SoftSkillRepository;
import dev.ddthanh.jobsgobe.service.iservice.JobIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService implements JobIService {
    private final JobRepository jobRepository;
    private final CareerRepository careerRepository;
    private final ProSkillRepository proSkillRepository;
    private  final SoftSkillRepository softSkillRepository;
    private final LanguageRepository languageRepository;

    public JobResponse getJobResponse(JobEntity job){
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .required(job.getRequired())
                .benefit(job.getBenefit())
                .city(job.getCity())
                .districts(job.getDistricts())
                .wards(job.getWards())
                .specificAddress(job.getSpecificAddress())
                .phone(job.getPhone())
                .certificate(job.getCertificate())
                .position(job.getPosition())
                .positionWork(job.getPositionWork())
                .gender(job.getGender())
                .ageStart(job.getAgeStart())
                .ageEnd(job.getAgeEnd())
                .numberYearExperienceStart(job.getNumberYearExperienceStart())
                .numberYearExperienceEnd(job.getNumberYearExperienceEnd())
                .salaryFrom(job.getSalaryFrom())
                .salaryTo(job.getSalaryTo())
                .natureOfWork(job.getNatureOfWork())
                .createAt(job.getCreateAt())
                .updateAt(job.getUpdateAt())
                .status(job.getStatus())
                .listCareer(job.getListCareer().stream().map(careerEntity -> {
                    return CareerRequest.builder()
                            .id(careerEntity.getId())
                            .name(careerEntity.getName())
                            .build();
                }).collect(Collectors.toList()))
                .listProSkill(job.getListProSkill().stream().map(proSkillEntity -> {
                    return ProSkillRequest.builder()
                            .id(proSkillEntity.getId())
                            .name(proSkillEntity.getName())
                            .build();
                }).collect(Collectors.toList()))
                .listSoftSkill(job.getListSoftSkill().stream().map(softSkillEntity -> {
                    return SoftSkillRequest.builder()
                            .id(softSkillEntity.getId())
                            .name(softSkillEntity.getName())
                            .build();
                }).collect(Collectors.toList()))
                .listLanguage(job.getListLanguage().stream().map(languageEntity -> {
                    return LanguageRequest.builder()
                            .id(languageEntity.getId())
                            .name(languageEntity.getName())
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }
    @Override
    public Response<List<JobResponse>> showAll() {
        List<JobResponse> jobResponses = jobRepository.findAll().stream().map(jobEntity -> {
            return getJobResponse(jobEntity);
        }).collect(Collectors.toList());
        return Response.<List<JobResponse>>builder()
                .setData(jobResponses)
                .build();
    }

    @Override
    public Response<JobResponse> showOneJob(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if(job == null) {
            return Response.<JobResponse>builder()
                    .setMessage("Not found")
                    .setSuccess(false)
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .setStatusCode(400)
                    .build();
        }
        return Response.<JobResponse>builder()
                .setData(getJobResponse(job))
                .build();
    }

    @Override
    public Response<JobResponse> create(JobRequest request) {
        JobEntity job = JobEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .required(request.getRequired())
                .benefit(request.getBenefit())
                .city(request.getCity())
                .districts(request.getDistricts())
                .wards(request.getWards())
                .specificAddress(request.getSpecificAddress())
                .phone(request.getPhone())
                .certificate(request.getCertificate())
                .position(request.getPosition())
                .positionWork(request.getPositionWork())
                .gender(request.getGender())
                .ageStart(request.getAgeStart())
                .ageEnd(request.getAgeEnd())
                .numberYearExperienceStart(request.getNumberYearExperienceStart())
                .numberYearExperienceEnd(request.getNumberYearExperienceEnd())
                .salaryFrom(request.getSalaryFrom())
                .salaryTo(request.getSalaryTo())
                .natureOfWork(request.getNatureOfWork())
                .createAt(new Date())
                .listCareer(new ArrayList<CareerEntity>())
                .listProSkill(new ArrayList<ProSkillEntity>())
                .listSoftSkill(new ArrayList<SoftSkillEntity>())
                .listLanguage(new ArrayList<LanguageEntity>())
                .build();

        //career
        for (CareerRequest careerRequest: request.getListCareer()) {
            CareerEntity careerEntity = careerRepository.findById(careerRequest.getId()).orElse(null);
            if(careerEntity != null){
                careerEntity.getListJob().add(job);
                job.getListCareer().add(careerEntity);
            }
        }

        //pro skill
        for (ProSkillRequest proSkillRequest : request.getListProSkill()) {
            ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkillRequest.getId()).orElse(null);
            if(proSkillEntity != null){
                proSkillEntity.getListJob().add(job);
                job.getListProSkill().add(proSkillEntity);
            }
        }

        //soft skill
        for (SoftSkillRequest softSkillRequest : request.getListSoftSkill()) {
            SoftSkillEntity softSkillEntity = softSkillRepository.findById(softSkillRequest.getId()).orElse(null);
            if(softSkillEntity != null){
                softSkillEntity.getListJob().add(job);
                job.getListSoftSkill().add(softSkillEntity);
            }
        }

        //language
        for (LanguageRequest languageRequest : request.getListLanguage()) {
            LanguageEntity languageEntity = languageRepository.findById(languageRequest.getId()).orElse(null);
            if(languageEntity != null){
                languageEntity.getListJob().add(job);
                job.getListLanguage().add(languageEntity);
            }
        }
        jobRepository.save(job);
        return Response.<JobResponse>builder()
                .setData(getJobResponse(job))
                .build();
    }

    @Override
    public Response<JobResponse> update(Long id, JobRequest request) {
        JobEntity jobOld = jobRepository.findById(id).orElse(null);
        if(jobOld == null){
            return Response.<JobResponse>builder()
                    .setMessage("Not found")
                    .setSuccess(false)
                    .setStatusCode(400)
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        JobEntity job = JobEntity.builder()
                .id(id)
                .title(request.getTitle())
                .description(request.getDescription())
                .required(request.getRequired())
                .benefit(request.getBenefit())
                .city(request.getCity())
                .districts(request.getDistricts())
                .wards(request.getWards())
                .specificAddress(request.getSpecificAddress())
                .phone(request.getPhone())
                .certificate(request.getCertificate())
                .position(request.getPosition())
                .positionWork(request.getPositionWork())
                .gender(request.getGender())
                .ageStart(request.getAgeStart())
                .ageEnd(request.getAgeEnd())
                .numberYearExperienceStart(request.getNumberYearExperienceStart())
                .numberYearExperienceEnd(request.getNumberYearExperienceEnd())
                .salaryFrom(request.getSalaryFrom())
                .salaryTo(request.getSalaryTo())
                .natureOfWork(request.getNatureOfWork())
                .updateAt(new Date())
                .listCareer(new ArrayList<CareerEntity>())
                .listProSkill(new ArrayList<ProSkillEntity>())
                .listSoftSkill(new ArrayList<SoftSkillEntity>())
                .listLanguage(new ArrayList<LanguageEntity>())
                .build();


        jobOld.getListCareer().clear();
        jobOld.getListProSkill().clear();
        jobOld.getListSoftSkill().clear();
        jobOld.getListLanguage().clear();
        //career
        for (CareerRequest careerRequest: request.getListCareer()) {
            CareerEntity careerEntity = careerRepository.findById(careerRequest.getId()).orElse(null);
            if(careerEntity != null){
                careerEntity.getListJob().add(job);
                job.getListCareer().add(careerEntity);
            }
        }

        //pro skill
        for (ProSkillRequest proSkillRequest : request.getListProSkill()) {
            ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkillRequest.getId()).orElse(null);
            if(proSkillEntity != null){
                proSkillEntity.getListJob().add(job);
                job.getListProSkill().add(proSkillEntity);
            }
        }

        //soft skill
        for (SoftSkillRequest softSkillRequest : request.getListSoftSkill()) {
            SoftSkillEntity softSkillEntity = softSkillRepository.findById(softSkillRequest.getId()).orElse(null);
            if(softSkillEntity != null){
                softSkillEntity.getListJob().add(job);
                job.getListSoftSkill().add(softSkillEntity);
            }
        }

        //language
        for (LanguageRequest languageRequest : request.getListLanguage()) {
            LanguageEntity languageEntity = languageRepository.findById(languageRequest.getId()).orElse(null);
            if(languageEntity != null){
                languageEntity.getListJob().add(job);
                job.getListLanguage().add(languageEntity);
            }
        }
        jobRepository.save(job);
        return Response.<JobResponse>builder()
                .setData(getJobResponse(job))
                .build();
    }

    @Override
    public void delete(Long id) {
        jobRepository.deleteById(id);
    }
}
