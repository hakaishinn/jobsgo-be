package dev.ddthanh.jobsgobe.payload.request.job;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CareerRequest {
    private Long id;
    private String name;
}
