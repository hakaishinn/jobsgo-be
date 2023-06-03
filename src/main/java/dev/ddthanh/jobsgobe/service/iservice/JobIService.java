package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.payload.request.job.JobRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.job.JobResponse;

import java.util.List;

public interface JobIService {
    public Response<List<JobResponse>> showAll();
    public Response<JobResponse> showOneJob(Long id);
    public Response<JobResponse> create(JobRequest request);
    public Response<JobResponse> update(Long id, JobRequest request);
    public  void delete(Long id);
}
