package dev.ddthanh.jobsgobe.controller.career;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import dev.ddthanh.jobsgobe.model.entity.LanguageEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.CareerService;
import dev.ddthanh.jobsgobe.service.impl.LanguageService;
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
public class CareerController {
    private final CareerService careerService;

    @GetMapping("/public/careers")
    public Response<List<CareerEntity>> getAll() {
        return careerService.getAll();
    }
}
