package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.language.LanguageRepository;
import dev.ddthanh.jobsgobe.service.iservice.LanguageIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService implements LanguageIService {
    private final LanguageRepository languageRepository;
    @Override
    public Response<List<LanguageEntity>> getAll() {
        List<LanguageEntity> listLanguage = languageRepository.findAll();
        return Response.<List<LanguageEntity>>builder()
                .setMessage("Success")
                .setData(listLanguage)
                .build();
    }
}
