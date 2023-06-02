package dev.ddthanh.jobsgobe.service.impl.auth;

import dev.ddthanh.jobsgobe.common.enums.Role;
import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.request.auth.AuthRequest;
import dev.ddthanh.jobsgobe.payload.request.auth.RegisterRequest;
import dev.ddthanh.jobsgobe.payload.response.BaseResponse;
import dev.ddthanh.jobsgobe.payload.response.auth.AuthResponse;
import dev.ddthanh.jobsgobe.repository.user.UserRepository;
import dev.ddthanh.jobsgobe.security.jwt.JwtService;
import dev.ddthanh.jobsgobe.service.iservice.AuthIService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthIService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse authenticate(AuthRequest request) {

        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        String accessToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .setUserId(user.getId())
                .setEmail(user.getEmail())
                .setAccessToken(accessToken)
                .setRoles(List.of(user.getRole().name()))
                .build();
    }


    public BaseResponse register(RegisterRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new DuplicateResourceException("userEmail", request.getEmail());
//        }
        UserEntity user = UserEntity.builder()
                .email(request.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword().trim()))
                .name(request.getName())
                .role(Role.of(request.getRole()))
                .build();
        userRepository.save(user);
        return BaseResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
