package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.request.user.CandidateRequest;
import dev.ddthanh.jobsgobe.payload.request.user.PasswordRequest;
import dev.ddthanh.jobsgobe.payload.request.user.RecruiterRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserIService {
    public Response<UserEntity> getOneUser(Long id);
    public Response<List<UserEntity>> getAllRecruiter();
    public Response<List<UserEntity>> getAllCandidate();

    public Response<List<UserEntity>> getAllRecruiterFeatured();

    public Response<UserEntity> getOneUserByEmail(String email);

    public Response<UserEntity> changePassword(Long id, PasswordRequest request);
    public Response<UserEntity> updateRecruiter(Long id, RecruiterRequest request);

    public Response<UserEntity> getAdmin();

    public Response<UserEntity> forgotPassword(String email);

    public Response<List<UserEntity>> searchRecruiter(String keyword);

    public Response<UserEntity> updateCandidate(Long id, CandidateRequest request);
    public Response<UserEntity> disableUser(Long id);

}
