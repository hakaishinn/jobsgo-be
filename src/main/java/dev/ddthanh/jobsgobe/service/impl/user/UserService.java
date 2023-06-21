package dev.ddthanh.jobsgobe.service.impl.user;

import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.user.UserRepository;
import dev.ddthanh.jobsgobe.service.iservice.UserIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserIService {
    private final UserRepository userRepository;
    @Override
    public Response<UserEntity> getOneUser(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return Response.<UserEntity>builder()
                .setMessage("Get user success")
                .setData(user)
                .build();
    }

    @Override
    public Response<List<UserEntity>> getAllRecruiter() {
        List<UserEntity> listRecruiter = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().name() == "RECRUITER")
                .collect(Collectors.toList());
        return Response.<List<UserEntity>>builder()
                .setMessage("Get list recruiter success")
                .setData(listRecruiter)
                .build();
    }
}
