package dev.ddthanh.jobsgobe.controller.user;

import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/public/user/{id}")
    public Response<UserEntity> getOneUser(@PathVariable Long id) {
        return userService.getOneUser(id);
    }

    @GetMapping("/public/recruiters")
    public Response<List<UserEntity>> getAllRecruiter() {
        return userService.getAllRecruiter();
    }
}
