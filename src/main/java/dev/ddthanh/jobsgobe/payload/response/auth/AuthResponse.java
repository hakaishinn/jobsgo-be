package dev.ddthanh.jobsgobe.payload.response.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ADMIN
 * @created on 4/3/2023
 */
@Getter
@Setter
@Builder(setterPrefix = "set")
public class AuthResponse {
    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";
    private Long userId;
    private String email;
    private String name;
    private List<String> roles;
}
