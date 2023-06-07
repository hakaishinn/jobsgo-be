package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.career.CareerRepository;
import dev.ddthanh.jobsgobe.repository.softSkill.SoftSkillRepository;
import dev.ddthanh.jobsgobe.service.iservice.CareerIService;
import dev.ddthanh.jobsgobe.service.iservice.SoftSkillIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerService implements CareerIService {
    private final CareerRepository careerRepository;
    @Override
    public Response<List<CareerEntity>> getAll() {
        List<CareerEntity> listCareer = careerRepository.findAll();
        return Response.<List<CareerEntity>>builder()
                .setMessage("Success")
                .setData(listCareer)
                .build();
    }
}
