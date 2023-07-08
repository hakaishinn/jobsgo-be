package dev.ddthanh.jobsgobe.controller.proSkill;

import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.payload.request.proskill.ProskillRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.ProSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

@RequiredArgsConstructor
public class ProSkillController {
    private final ProSkillService proSkillService;

    @GetMapping("/public/proSkills")
    public Response<List<ProSkillEntity>> getAll() {
        return proSkillService.getAll();
    }

    @PostMapping("/admin/create-proskill")
    public Response<ProSkillEntity> createProSkill(@RequestBody ProskillRequest request){
        Response<ProSkillEntity> response = proSkillService.createProskill(request);
        return response;
    }

    @PutMapping("/admin/update-proskill/{id}")
    public Response<ProSkillEntity> updateProSkill(@PathVariable Long id, @RequestBody ProskillRequest request){
        Response<ProSkillEntity> response = proSkillService.updateProskill(id,request);
        return response;
    }
}
