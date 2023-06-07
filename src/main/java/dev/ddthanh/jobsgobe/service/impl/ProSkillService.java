package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.proSkill.ProSkillRepository;
import dev.ddthanh.jobsgobe.service.iservice.ProSkillIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProSkillService implements ProSkillIService {
    private final ProSkillRepository proSkillRepository;
    @Override
    public Response<List<ProSkillEntity>> getAll() {
        List<ProSkillEntity> listProSkill = proSkillRepository.findAll();
        return Response.<List<ProSkillEntity>>builder()
                .setMessage("Success")
                .setData(listProSkill)
                .build();
    }
}
