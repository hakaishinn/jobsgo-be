package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.ApplyEntity;
import dev.ddthanh.jobsgobe.payload.request.apply.BaseApply;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeApplyResponse;

import java.util.List;

public interface ApplyIService {
    public Response<ApplyEntity> apply(BaseApply request);
    public  Response<ApplyEntity> checkApply(Long jobId, Long candidateId);
    public Response<List<ResumeApplyResponse>> getAllResumeApply(Long recruiterId);
    public Response<List<ResumeApplyResponse>> getAllResumeApprove(Long recruiterId);
    public Response<List<ResumeApplyResponse>> getAllResumeConsider(Long recruiterId);
    public Response<List<ResumeApplyResponse>> getAllResumeDenied(Long recruiterId);

    public Response<ApplyEntity> Approve(Long id);
    public Response<ApplyEntity> Consider(Long id);
    public Response<ApplyEntity> Denied(Long id);

    public Response<ApplyEntity> deleteById(Long id);
}
