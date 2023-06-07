package dev.ddthanh.jobsgobe.service.impl.job;

import dev.ddthanh.jobsgobe.common.constants.TypeJob;
import dev.ddthanh.jobsgobe.model.entity.*;
import dev.ddthanh.jobsgobe.payload.request.job.*;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.job.JobResponse;
import dev.ddthanh.jobsgobe.repository.career.CareerRepository;
import dev.ddthanh.jobsgobe.repository.job.JobRepository;
import dev.ddthanh.jobsgobe.repository.language.LanguageRepository;
import dev.ddthanh.jobsgobe.repository.proSkill.ProSkillRepository;
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
    private final SoftSkillRepository softSkillRepository;
    private final LanguageRepository languageRepository;

    public JobResponse getJobResponse(JobEntity job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .required(job.getRequired())
                .benefit(job.getBenefit())
                .city(job.getCity())
                .district(job.getDistrict())
                .ward(job.getWard())
                .specificAddress(job.getSpecificAddress())
                .phone(job.getPhone())
                .degree(job.getDegree())
                .typePosition(job.getTypePosition())
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
                .listCareer(job.getListCareer())
                .listProSkill(job.getListProSkill())
                .listSoftSkill(job.getListSoftSkill())
                .listLanguage(job.getListLanguage())
                .build();
    }

    @Override
    public Response<List<JobResponse>> showAll() {
        List<JobResponse> jobResponses = jobRepository.findAll().stream().map(this::getJobResponse
        ).collect(Collectors.toList());
        return Response.<List<JobResponse>>builder()
                .setData(jobResponses)
                .build();
    }

    @Override
    public Response<List<JobResponse>> showJobApply() {
        List<JobResponse> jobResponses = jobRepository.findAll().stream()
                .filter(jobEntity -> jobEntity.getStatus() == TypeJob.APPLY)
                .map(this::getJobResponse)
                .collect(Collectors.toList());
        return Response.<List<JobResponse>>builder()
                .setData(jobResponses)
                .build();
    }

    @Override
    public Response<List<JobResponse>> showJobPause() {
        List<JobResponse> jobResponses = jobRepository.findAll().stream()
                .filter(jobEntity -> jobEntity.getStatus() == TypeJob.PAUSE)
                .map(this::getJobResponse)
                .collect(Collectors.toList());
        return Response.<List<JobResponse>>builder()
                .setData(jobResponses)
                .build();
    }

    @Override
    public Response<List<JobResponse>> showJobExpired() {
        List<JobResponse> jobResponses = jobRepository.findAll().stream()
                .filter(jobEntity -> jobEntity.getStatus() == TypeJob.EXPIRED)
                .map(this::getJobResponse)
                .collect(Collectors.toList());
        return Response.<List<JobResponse>>builder()
                .setData(jobResponses)
                .build();
    }

    @Override
    public Response<List<JobResponse>> showPending() {
        List<JobResponse> jobResponses = jobRepository.findAll().stream()
                .filter(jobEntity -> jobEntity.getStatus() == TypeJob.PENDING)
                .map(this::getJobResponse)
                .collect(Collectors.toList());
        return Response.<List<JobResponse>>builder()
                .setData(jobResponses)
                .build();
    }

    @Override
    public Response<JobResponse> showOneJob(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if (job == null) {
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
                .district(request.getDistrict())
                .ward(request.getWard())
                .specificAddress(request.getSpecificAddress())
                .phone(request.getPhone())
                .degree(request.getDegree())
                .typePosition(request.getTypePosition())
                .gender(request.getGender())
                .ageStart(request.getAgeStart())
                .ageEnd(request.getAgeEnd())
                .numberYearExperienceStart(request.getNumberYearExperienceStart())
                .numberYearExperienceEnd(request.getNumberYearExperienceEnd())
                .salaryFrom(request.getSalaryFrom())
                .salaryTo(request.getSalaryTo())
                .natureOfWork(request.getNatureOfWork())
                .createAt(new Date())
                .status(TypeJob.PENDING)
                .listCareer(new ArrayList<>())
                .listProSkill(new ArrayList<>())
                .listSoftSkill(new ArrayList<>())
                .listLanguage(new ArrayList<>())
                .build();

        //career
        for (Long careerId : request.getListCareer()) {
            CareerEntity careerEntity = careerRepository.findById(careerId).orElse(null);
            if (careerEntity != null) {
                careerEntity.getListJob().add(job);
                job.getListCareer().add(careerEntity);
            }
        }

        //pro skill
        for (Long proSkillId : request.getListProSkill()) {
            ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkillId).orElse(null);
            if (proSkillEntity != null) {
                proSkillEntity.getListJob().add(job);
                job.getListProSkill().add(proSkillEntity);
            }
        }

        //soft skill
        for (Long softSkillId : request.getListSoftSkill()) {
            SoftSkillEntity softSkillEntity = softSkillRepository.findById(softSkillId).orElse(null);
            if (softSkillEntity != null) {
                softSkillEntity.getListJob().add(job);
                job.getListSoftSkill().add(softSkillEntity);
            }
        }

        //language
        for (Long languageId : request.getListLanguage()) {
            LanguageEntity languageEntity = languageRepository.findById(languageId).orElse(null);
            if (languageEntity != null) {
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
        if (jobOld == null) {
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
                .district(request.getDistrict())
                .ward(request.getWard())
                .specificAddress(request.getSpecificAddress())
                .phone(request.getPhone())
                .degree(request.getDegree())
                .typePosition(request.getTypePosition())
                .gender(request.getGender())
                .ageStart(request.getAgeStart())
                .ageEnd(request.getAgeEnd())
                .numberYearExperienceStart(request.getNumberYearExperienceStart())
                .numberYearExperienceEnd(request.getNumberYearExperienceEnd())
                .salaryFrom(request.getSalaryFrom())
                .salaryTo(request.getSalaryTo())
                .natureOfWork(request.getNatureOfWork())
                .updateAt(new Date())
                .listCareer(new ArrayList<>())
                .listProSkill(new ArrayList<>())
                .listSoftSkill(new ArrayList<>())
                .listLanguage(new ArrayList<>())
                .build();


        jobOld.getListCareer().clear();
        jobOld.getListProSkill().clear();
        jobOld.getListSoftSkill().clear();
        jobOld.getListLanguage().clear();
        //career
        for (Long careerId : request.getListCareer()) {
            CareerEntity careerEntity = careerRepository.findById(careerId).orElse(null);
            if (careerEntity != null) {
                careerEntity.getListJob().add(job);
                job.getListCareer().add(careerEntity);
            }
        }

        //pro skill
        for (Long proSkillId : request.getListProSkill()) {
            ProSkillEntity proSkillEntity = proSkillRepository.findById(proSkillId).orElse(null);
            if (proSkillEntity != null) {
                proSkillEntity.getListJob().add(job);
                job.getListProSkill().add(proSkillEntity);
            }
        }

        //soft skill
        for (Long softSkillId : request.getListSoftSkill()) {
            SoftSkillEntity softSkillEntity = softSkillRepository.findById(softSkillId).orElse(null);
            if (softSkillEntity != null) {
                softSkillEntity.getListJob().add(job);
                job.getListSoftSkill().add(softSkillEntity);
            }
        }

        //language
        for (Long languageId : request.getListLanguage()) {
            LanguageEntity languageEntity = languageRepository.findById(languageId).orElse(null);
            if (languageEntity != null) {
                languageEntity.getListJob().add(job);
                job.getListLanguage().add(languageEntity);
            }
        }
        jobRepository.save(job);
        return Response.<JobResponse>builder()
                .setStatus(HttpStatus.CREATED)
                .setStatusCode(201)
                .setSuccess(true)
                .setData(getJobResponse(job))
                .build();
    }

    @Override
    public void delete(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Response<JobResponse> changeStatusApply(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return Response.<JobResponse>builder()
                    .setMessage("Not found")
                    .setSuccess(false)
                    .setStatusCode(400)
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        job.setStatus(TypeJob.APPLY);
        jobRepository.save(job);
        return Response.<JobResponse>builder()
                .setData(getJobResponse(job))
                .build();
    }

    @Override
    public Response<JobResponse> changeStatusPause(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return Response.<JobResponse>builder()
                    .setMessage("Not found")
                    .setSuccess(false)
                    .setStatusCode(400)
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        job.setStatus(TypeJob.PAUSE);
        jobRepository.save(job);
        return Response.<JobResponse>builder()
                .setData(getJobResponse(job))
                .build();
    }

    @Override
    public Response<JobResponse> changeStatusExpired(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return Response.<JobResponse>builder()
                    .setMessage("Not found")
                    .setSuccess(false)
                    .setStatusCode(400)
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        job.setStatus(TypeJob.EXPIRED);
        jobRepository.save(job);
        return Response.<JobResponse>builder()
                .setData(getJobResponse(job))
                .build();
    }

    @Override
    public Response<JobResponse> changeStatusPending(Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return Response.<JobResponse>builder()
                    .setMessage("Not found")
                    .setSuccess(false)
                    .setStatusCode(400)
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        job.setStatus(TypeJob.PENDING);
        jobRepository.save(job);
        return Response.<JobResponse>builder()
                .setData(getJobResponse(job))
                .build();
    }
}
