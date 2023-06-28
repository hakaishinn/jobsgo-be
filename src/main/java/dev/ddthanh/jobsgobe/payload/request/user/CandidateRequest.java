package dev.ddthanh.jobsgobe.payload.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CandidateRequest {
    private String fullName;
    private Date birthDay;
    private String phone;
    private String facebook;
    private String twitter;
    private String linkedin;
}
