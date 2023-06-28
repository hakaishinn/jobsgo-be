package dev.ddthanh.jobsgobe.controller.usedPackage;

import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import dev.ddthanh.jobsgobe.model.entity.UsedPackageEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.usedPackage.UsedPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsedPackageController {
    private final UsedPackageService usedPackageService;
    @GetMapping("/public/usedPackage/check/{id}")
    public Response<UsedPackageEntity> checkUsedPackage(@PathVariable Long id) {
        return usedPackageService.checkUsedPackage(id);
    }
    @GetMapping("/public/usedPackage/cancelAllPackage/recruiter/{id}")
    public void cancelAllPackageByRecruiterId(@PathVariable Long id) {
        usedPackageService.cancelAllPackageByRecruiterId(id);
    }


}
