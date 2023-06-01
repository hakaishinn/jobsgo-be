package dev.ddthanh.jobsgobe.controller.auth;

import dev.ddthanh.jobsgobe.model.entity.ResumeEntity;
import dev.ddthanh.jobsgobe.payload.request.auth.RegisterRequest;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import dev.ddthanh.jobsgobe.service.impl.resume.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/resume")
@CrossOrigin
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    @PostMapping("/create")
    public Response<ResumeResponse> create(@RequestBody ResumeRequest request) {
        ResumeResponse resumeResponse = resumeService.create(request);
        return Response.<ResumeResponse>builder()
                .setData(resumeResponse)
                .build();
    }
}
