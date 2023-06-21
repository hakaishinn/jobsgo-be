package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;

import java.util.List;

public interface UserIService {
    public Response<UserEntity> getOneUser(Long id);
    public Response<List<UserEntity>> getAllRecruiter();
}
