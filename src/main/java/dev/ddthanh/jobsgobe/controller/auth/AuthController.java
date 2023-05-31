package dev.ddthanh.jobsgobe.controller.auth;

import dev.ddthanh.jobsgobe.converter.AuthConverter;
import dev.ddthanh.jobsgobe.payload.request.AuthRequest;
import dev.ddthanh.jobsgobe.payload.request.RegisterRequest;
import dev.ddthanh.jobsgobe.payload.response.BaseResponse;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.auth.AuthResponse;
import dev.ddthanh.jobsgobe.service.impl.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authenticationService;
    private final AuthConverter authConverter;

    @PostMapping("/register")
    public Response<BaseResponse> register(@RequestBody RegisterRequest request) {
        BaseResponse response = authenticationService.register(request);
        return authConverter.successResponse(response.getId(), response.getName(), response.getEmail());
    }

    @PostMapping("/login")
    public Response<AuthResponse> authenticateUser(@RequestBody AuthRequest request) {
        AuthResponse response = authenticationService.authenticate(request);
        return Response.<AuthResponse>builder()
                .setMessage("Login successful")
                .setData(response)
                .build();
    }
}
