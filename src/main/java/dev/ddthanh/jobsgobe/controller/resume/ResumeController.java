package dev.ddthanh.jobsgobe.controller.resume;

import dev.ddthanh.jobsgobe.common.enums.Role;
import dev.ddthanh.jobsgobe.payload.request.resume.ResumeRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import dev.ddthanh.jobsgobe.service.impl.resume.ResumeService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/public/resumes")
    public Response<List<ResumeResponse>> showAll(){
        Response<List<ResumeResponse>> listResponse = resumeService.showAll();
        return listResponse;
    }
    @GetMapping("/resumes/{id}")
    @Secured({"CANDIDATE", "RECRUITER", "ADMIN"})
    public Response<ResumeResponse> showOneResume(@PathVariable Long id) {
        Response<ResumeResponse> resumeResponse = resumeService.showOneResume(id);
        return resumeResponse;
    }

    @PostMapping("/resumes")
    @Secured("CANDIDATE")
    public Response<ResumeResponse> create(@RequestBody ResumeRequest request) {
        Response<ResumeResponse> resumeResponse = resumeService.create(request);
        return resumeResponse;
    }

    @PutMapping("/resumes/{id}")
    @Secured("CANDIDATE")
    public  Response<ResumeResponse> update(@PathVariable Long id, @RequestBody ResumeRequest request){
        Response<ResumeResponse> resumeResponse = resumeService.update(id, request);
        return resumeResponse;
    }
    @DeleteMapping("/resumes/{id}")
    @Secured("CANDIDATE")
    public  Response<ResumeResponse> delete(@PathVariable Long id){
        Response<ResumeResponse> resumeResponse = resumeService.delete(id);
        return resumeResponse;
    }

    @DeleteMapping("/resumes/proSkill/{id}")
    @Secured("CANDIDATE")
    public void deleteProSkill(@PathVariable Long id){
        resumeService.deleteResumeProSkillById(id);
    }

    @DeleteMapping("/resumes/workExp/{id}")
    @Secured("CANDIDATE")
    public void deleteWorkExp(@PathVariable Long id){
        resumeService.deleteResumeWorkExpById(id);
    }

    @DeleteMapping("/resumes/education/{id}")
    @Secured("CANDIDATE")
    public void deleteResumeEducation(@PathVariable Long id){
        resumeService.deleteResumeEducationById(id);
    }

    @DeleteMapping("/resumes/language/{id}")
    @Secured("CANDIDATE")
    public void deleteResumeLanguage(@PathVariable Long id){
        resumeService.deleteResumeLanguageById(id);
    }

    @DeleteMapping("/resumes/softSkill/{id}")
    @Secured("CANDIDATE")
    public void deleteResumeSoftSkill(@PathVariable Long id){
        resumeService.deleteResumeSoftSkillById(id);
    }

    @DeleteMapping("/resumes/hobby/{id}")
    @Secured("CANDIDATE")
    public void deleteResumeHobby(@PathVariable Long id){
        resumeService.deleteResumeHobbyById(id);
    }

}
