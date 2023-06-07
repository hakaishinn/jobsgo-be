package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;

import java.util.List;

public interface CareerIService {
    public Response<List<CareerEntity>> getAll();
}
