package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.payload.request.job.JobRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.job.JobResponse;

import java.util.List;

public interface JobIService {
    public Response<List<JobResponse>> showAll();
    public Response<List<JobResponse>> showJobApply();
    public Response<List<JobResponse>> showJobPause();
    public Response<List<JobResponse>> showJobExpired();
    public Response<List<JobResponse>> showPending();

    public Response<JobResponse> showOneJob(Long id);
    public Response<JobResponse> create(JobRequest request);
    public Response<JobResponse> update(Long id, JobRequest request);
    public  void delete(Long id);

    public Response<JobResponse> changeStatusApply(Long id);
    public Response<JobResponse> changeStatusPause(Long id);
    public Response<JobResponse> changeStatusExpired(Long id);
    public Response<JobResponse> changeStatusPending(Long id);

}
