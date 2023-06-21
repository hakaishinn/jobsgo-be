package dev.ddthanh.jobsgobe.controller.job;

import dev.ddthanh.jobsgobe.model.entity.JobEntity;
import dev.ddthanh.jobsgobe.payload.request.job.JobRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.job.JobApplyResponse;
import dev.ddthanh.jobsgobe.payload.response.job.JobResponse;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import dev.ddthanh.jobsgobe.service.impl.job.JobService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/jobs")
    @Secured({"RECRUITER", "ADMIN"})
    public Response<List<JobResponse>> showAll() {
        Response<List<JobResponse>> jobResponse = jobService.showAll();
        return jobResponse;
    }

    @GetMapping("/public/jobs/open")
    public Response<List<JobResponse>> showJobsOpen() {
        Response<List<JobResponse>> jobResponse = jobService.showJobOpen();
        return jobResponse;
    }

    @GetMapping("/jobs/pause")
    @Secured({"RECRUITER", "ADMIN"})
    public Response<List<JobResponse>> showJobsPause() {
        Response<List<JobResponse>> jobResponse = jobService.showJobPause();
        return jobResponse;
    }

    @GetMapping("/jobs/expired")
    @Secured({"RECRUITER", "ADMIN"})
    public Response<List<JobResponse>> showJobsExpired() {
        Response<List<JobResponse>> jobResponse = jobService.showJobExpired();
        return jobResponse;
    }

    @GetMapping("/jobs/pending")
    @Secured({"RECRUITER", "ADMIN"})
    public Response<List<JobResponse>> showJobsPending() {
        Response<List<JobResponse>> jobResponse = jobService.showPending();
        return jobResponse;
    }
    @GetMapping("/public/jobs/{id}")
    public Response<JobResponse> showOneJob(@PathVariable Long id) {
        Response<JobResponse> jobResponse = jobService.showOneJob(id);
        return jobResponse;
    }

    @PostMapping("/jobs")
    @Secured("RECRUITER")
    public Response<JobResponse> create(@RequestBody JobRequest request) {
        Response<JobResponse> jobResponse = jobService.create(request);
        return jobResponse;
    }

    @PutMapping("/jobs/{id}")
    @Secured("RECRUITER")
    public Response<JobResponse> update(@PathVariable Long id, @RequestBody JobRequest request) {
        Response<JobResponse> jobResponse = jobService.update(id, request);
        return jobResponse;
    }

    @PutMapping("/jobs/changeStatusApply/{id}")
    @Secured({"RECRUITER", "ADMIN"})
    public Response<JobResponse> changeStatusApply(@PathVariable Long id) {
        Response<JobResponse> jobResponse = jobService.changeStatusApply(id);
        return jobResponse;
    }

    @PutMapping("/jobs/changeStatusPause/{id}")
    @Secured("RECRUITER")
    public Response<JobResponse> changeStatusPause(@PathVariable Long id) {
        Response<JobResponse> jobResponse = jobService.changeStatusPause(id);
        return jobResponse;
    }

    @PutMapping("/jobs/changeStatusExpired/{id}")
    @Secured("ADMIN")
    public Response<JobResponse> changeStatusExpired(@PathVariable Long id) {
        Response<JobResponse> jobResponse = jobService.changeStatusExpired(id);
        return jobResponse;
    }

    @DeleteMapping("/jobs/{id}")
    @Secured({"RECRUITER", "ADMIN"})
    public void deleteById(@PathVariable Long id){
        jobService.delete(id);
    }

    @GetMapping("/jobs/apply/candidate/{id}")
    @Secured("CANDIDATE")
    public  Response<List<JobApplyResponse>> getAllJobApplyByCandidateId(@PathVariable Long id){
        Response<List<JobApplyResponse>> listJob = jobService.getAllJobApplyByCandidateId(id);
        return listJob;
    }

    @GetMapping("/public/jobs/search")
    public Response<List<JobResponse>> search(@RequestParam(name = "keyword", required = false) String keyword,
                                             @RequestParam(name = "address", required = false) String address){
        return jobService.search(keyword, address);
    }

    @GetMapping("/public/jobs/careers/{id}")
    public Response<List<JobResponse>> search(@PathVariable Long id){
        return jobService.showJobByCareerId(id);
    }
    @GetMapping("/public/jobs/noExp")
    public Response<List<JobResponse>> showJobNoExp(){
        return jobService.showJobNoExp();
    }
}
