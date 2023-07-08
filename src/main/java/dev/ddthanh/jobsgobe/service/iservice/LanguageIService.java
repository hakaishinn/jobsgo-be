package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;

import java.util.List;

public interface LanguageIService {
    public Response<List<LanguageEntity>> getAll();
    public Response<LanguageEntity> createLanguage(LanguageEntity entity);
    public Response<LanguageEntity> updateLanguage(Long id , LanguageEntity entity);

}
