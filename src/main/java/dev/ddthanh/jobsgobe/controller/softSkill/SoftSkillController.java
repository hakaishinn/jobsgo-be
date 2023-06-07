package dev.ddthanh.jobsgobe.controller.softSkill;

import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.SoftSkillService;
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
public class SoftSkillController {
    private final SoftSkillService softSkillService;

    @GetMapping("/public/softSkills")
    public Response<List<SoftSkillEntity>> getAll() {
        return softSkillService.getAll();
    }
}
