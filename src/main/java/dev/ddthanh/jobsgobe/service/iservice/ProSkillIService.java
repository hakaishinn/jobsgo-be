package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.payload.request.proskill.ProskillRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;

import java.util.List;

public interface ProSkillIService {
    public Response<List<ProSkillEntity>> getAll();
    public Response<ProSkillEntity> createProskill(ProskillRequest request);
    public Response<ProSkillEntity> updateProskill(Long id, ProskillRequest request);

}
