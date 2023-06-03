package dev.ddthanh.jobsgobe.controller.job;

import dev.ddthanh.jobsgobe.payload.request.job.JobRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.job.JobResponse;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import dev.ddthanh.jobsgobe.service.impl.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/public/jobs")
    public Response<List<JobResponse>> showAll() {
        Response<List<JobResponse>> jobResponse = jobService.showAll();
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
    public Response<JobResponse> update(@PathVariable Long id,@RequestBody JobRequest request) {
        Response<JobResponse> jobResponse = jobService.update(id, request);
        return jobResponse;
    }
    @DeleteMapping("/jobs/{id}")
    @Secured({"RECRUITER", "ADMIN"})
    public void deleteById(@PathVariable Long id){
        jobService.delete(id);
    }
}
