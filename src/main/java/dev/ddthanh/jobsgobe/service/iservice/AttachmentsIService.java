package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.AttachmentsEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;

import java.util.List;

public interface AttachmentsIService {
    public Response<AttachmentsEntity> create(Long id, AttachmentsEntity request);

    public Response<List<AttachmentsEntity>> getAttachmentsByCandidateId(Long id);

    public Response<AttachmentsEntity> update(Long id, AttachmentsEntity request);
    public void deleteById(Long id);
}
