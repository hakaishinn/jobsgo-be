package dev.ddthanh.jobsgobe.controller.language;

import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.LanguageService;
import dev.ddthanh.jobsgobe.service.impl.SoftSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping("/public/languages")
    public Response<List<LanguageEntity>> getAll() {
        return languageService.getAll();
    }
}
