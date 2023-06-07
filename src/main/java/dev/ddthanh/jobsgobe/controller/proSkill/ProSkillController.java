package dev.ddthanh.jobsgobe.controller.proSkill;

import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.ProSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ProSkillController {
    private final ProSkillService proSkillService;

    @GetMapping("/public/proSkills")
    public Response<List<ProSkillEntity>> getAll() {
        return proSkillService.getAll();
    }
}
