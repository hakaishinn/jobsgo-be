package dev.ddthanh.jobsgobe.controller.resume;

import dev.ddthanh.jobsgobe.payload.request.resume.ResumeRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import dev.ddthanh.jobsgobe.service.impl.resume.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    @GetMapping("/resumes")
    public Response<List<ResumeResponse>> showAll(){
        Response<List<ResumeResponse>> listResponse = resumeService.showAll();
        return listResponse;
    }
    @GetMapping("/resumes/{id}")
    public Response<ResumeResponse> showOneResume(@PathVariable Long id) {
        Response<ResumeResponse> resumeResponse = resumeService.showOneResume(id);
        return resumeResponse;
    }

    @PostMapping("/resumes")
    public Response<ResumeResponse> create(@RequestBody ResumeRequest request) {
        Response<ResumeResponse> resumeResponse = resumeService.create(request);
        return resumeResponse;
    }

    @PutMapping("/resumes/{id}")
    public  Response<ResumeResponse> update(@PathVariable Long id, @RequestBody ResumeRequest request){
        Response<ResumeResponse> resumeResponse = resumeService.update(id, request);
        return resumeResponse;
    }
    @DeleteMapping("/resumes/{id}")
    public  Response<ResumeResponse> delete(@PathVariable Long id){
        Response<ResumeResponse> resumeResponse = resumeService.delete(id);
        return resumeResponse;
    }

    @DeleteMapping("/resumes/proSkill/{id}")
    public void deleteProSkill(@PathVariable Long id){
        resumeService.deleteResumeProSkillById(id);
    }

    @DeleteMapping("/resumes/workExp/{id}")
    public void deleteWorkExp(@PathVariable Long id){
        resumeService.deleteResumeWorkExpById(id);
    }

    @DeleteMapping("/resumes/education/{id}")
    public void deleteResumeEducation(@PathVariable Long id){
        resumeService.deleteResumeEducationById(id);
    }

    @DeleteMapping("/resumes/language/{id}")
    public void deleteResumeLanguage(@PathVariable Long id){
        resumeService.deleteResumeLanguageById(id);
    }

    @DeleteMapping("/resumes/softSkill/{id}")
    public void deleteResumeSoftSkill(@PathVariable Long id){
        resumeService.deleteResumeSoftSkillById(id);
    }

    @DeleteMapping("/resumes/hobby/{id}")
    public void deleteResumeHobby(@PathVariable Long id){
        resumeService.deleteResumeHobbyById(id);
    }

}
