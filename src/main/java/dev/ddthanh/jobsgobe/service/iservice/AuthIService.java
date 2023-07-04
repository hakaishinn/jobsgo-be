package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.payload.request.auth.AuthRequest;
import dev.ddthanh.jobsgobe.payload.request.auth.RegisterRequest;
import dev.ddthanh.jobsgobe.payload.response.BaseResponse;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.auth.AuthResponse;

public interface AuthIService {
    public Response<AuthResponse> authenticate(AuthRequest request);
    public Response<AuthResponse> authenticateGoogleAndFacebook(RegisterRequest request);
    public Response<BaseResponse> register(RegisterRequest request);
}
