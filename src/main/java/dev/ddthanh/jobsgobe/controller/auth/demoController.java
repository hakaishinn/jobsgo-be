package dev.ddthanh.jobsgobe.controller.auth;

import dev.ddthanh.jobsgobe.common.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@Secured({"RECRUITER"})
public class demoController {
    private final String recruiter = "RECRUITER";

    @GetMapping("/hello")
    @Secured(value = "RECRUITER")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello recruiter");
    }
}
