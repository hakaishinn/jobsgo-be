package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;

import java.util.List;

public interface SoftSkillIService {
    public Response<List<SoftSkillEntity>> getAll();
    Response<SoftSkillEntity> createSoftskill(SoftSkillEntity request);

    Response<SoftSkillEntity> updateSoftskill(Long id, SoftSkillEntity request);

}
