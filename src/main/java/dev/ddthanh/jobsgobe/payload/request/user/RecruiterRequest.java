package dev.ddthanh.jobsgobe.payload.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecruiterRequest {
    private String image;
    private String emailCompany;
    private String nameCompany;
    private String shortName;
    private String phone;
    private String specificAddress;
    private String website;
    private String facebook;
    private String twitter;
    private String linkedin;
    private String description;
}
