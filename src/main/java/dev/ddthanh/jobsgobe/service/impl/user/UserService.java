package dev.ddthanh.jobsgobe.service.impl.user;

import dev.ddthanh.jobsgobe.model.entity.UsedPackageEntity;
import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.request.EmailRequest;
import dev.ddthanh.jobsgobe.payload.request.user.PasswordRequest;
import dev.ddthanh.jobsgobe.payload.request.user.RecruiterRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.auth.AuthResponse;
import dev.ddthanh.jobsgobe.repository.user.UserRepository;
import dev.ddthanh.jobsgobe.service.impl.EmailSenderService;
import dev.ddthanh.jobsgobe.service.iservice.UserIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserIService {
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;

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
                .filter(user -> user.getRole().name() == "RECRUITER" && !user.isLock())
                .collect(Collectors.toList());
        return Response.<List<UserEntity>>builder()
                .setMessage("Get list recruiter success")
                .setData(listRecruiter)
                .build();
    }

    @Override
    public Response<List<UserEntity>> getAllRecruiterFeatured() {
        List<UserEntity> listRecruiter = getAllRecruiter().getData();
        List<UserEntity> result = new ArrayList<>();
        for (UserEntity recruiter: listRecruiter) {
            for (UsedPackageEntity used: recruiter.getListUsedPackage()) {
                if(used.getPackageEntity().getId() == 2 && used.isStatus() && !recruiter.isLock()){
                    result.add(recruiter);
                    break;
                }
            }
        }
        return Response.<List<UserEntity>>builder()
                .setMessage("Get list recruiter featured success")
                .setData(result)
                .build();
    }

    @Override
    public Response<UserEntity> getOneUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if(user != null) {
            return Response.<UserEntity>builder()
                    .setData(userRepository.findByEmail(email).get())
                    .build();
        }
        return Response.<UserEntity>builder()
                .setSuccess(false)
                .setStatusCode(400)
                .setStatus(HttpStatus.BAD_REQUEST)
                .setMessage("Not found")
                .build();
    }

    @Override
    public Response<UserEntity> changePassword(Long id, PasswordRequest request) {
        UserEntity user = userRepository.findById(id).orElse(null);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(user != null){
            if(!passwordEncoder.matches(request.getOldPassword().trim(), user.getPassword())){
                return Response.<UserEntity>builder()
                        .setMessage("Mật khẩu cũ không đúng")
                        .setStatus(HttpStatus.BAD_REQUEST)
                        .setSuccess(false)
                        .setStatusCode(400)
                        .build();
            } else {
                user.setPassword(passwordEncoder.encode(request.getNewPassword().trim()));
                userRepository.save(user);
            }
        }
        return Response.<UserEntity>builder()
                .setData(user)
                .setMessage("Change password success")
                .build();
    }

    @Override
    public Response<UserEntity> updateRecruiter(Long id, RecruiterRequest request) {
        UserEntity recruiter = userRepository.findById(id).orElse(null);
        if(recruiter != null){
            recruiter.setImage(request.getImage());
            recruiter.setEmailCompany(request.getEmailCompany());
            recruiter.setName(request.getNameCompany());
            recruiter.setShortName(request.getShortName());
            recruiter.setPhone(request.getPhone());
            recruiter.setSpecificAddress(request.getSpecificAddress());
            recruiter.setWards(request.getWard());
            recruiter.setDistricts(request.getDistrict());
            recruiter.setCity(request.getCity());
            recruiter.setWebsite(request.getWebsite());
            recruiter.setFacebook(request.getFacebook());
            recruiter.setTwitter(request.getTwitter());
            recruiter.setLinkedin(request.getLinkedin());
            recruiter.setDescription(request.getDescription());

            userRepository.save(recruiter);
            return Response.<UserEntity>builder()
                    .setData(recruiter)
                    .setMessage("Update success")
                    .build();
        }
        return Response.<UserEntity>builder()
                .setMessage("User doesn't exist")
                .setStatus(HttpStatus.BAD_REQUEST)
                .setSuccess(false)
                .setStatusCode(400)
                .build();
    }

    @Override
    public Response<UserEntity> getAdmin() {
        UserEntity admin = userRepository.findAdmin().get();
        return Response.<UserEntity>builder()
                .setData(admin)
                .setMessage("Get admin success")
                .build();
    }

    @Override
    public Response<UserEntity> forgotPassword(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if(user != null){
            String newPassword = getRandomNumberString().trim();
            user.setPassword(passwordEncoder.encode(newPassword));
            EmailRequest emailRequest = EmailRequest.builder()
                    .toEmail(email)
                    .subject("[JobsGO] Reset Password")
                    .body("Mật khẩu mới của bạn là: " + newPassword)
                    .build();
            emailSenderService.sendEmail(emailRequest);
            userRepository.save(user);

            return Response.<UserEntity>builder()
                    .setData(user)
                    .setMessage("Reset Password success")
                    .build();
        }
        return Response.<UserEntity>builder()
                .setSuccess(false)
                .setStatusCode(400)
                .setMessage("Reset Password failed")
                .build();
    }

    @Override
    public Response<List<UserEntity>> searchRecruiter(String keyword) {
        List<UserEntity> listRecruiter = userRepository.searchRecruiter(keyword);
        return Response.<List<UserEntity>>builder()
                .setData(listRecruiter)
                .setMessage("Get recruiter success")
                .build();
    }

    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
