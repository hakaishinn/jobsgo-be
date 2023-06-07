package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.softSkill.SoftSkillRepository;
import dev.ddthanh.jobsgobe.service.iservice.SoftSkillIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftSkillService implements SoftSkillIService {
    private final SoftSkillRepository softSkillRepository;
    @Override
    public Response<List<SoftSkillEntity>> getAll() {
        List<SoftSkillEntity> listSoftSkill = softSkillRepository.findAll();
        return Response.<List<SoftSkillEntity>>builder()
                .setMessage("Success")
                .setData(listSoftSkill)
                .build();
    }
}
