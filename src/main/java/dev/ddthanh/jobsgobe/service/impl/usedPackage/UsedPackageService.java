package dev.ddthanh.jobsgobe.service.impl.usedPackage;

import dev.ddthanh.jobsgobe.model.entity.PackageEntity;
import dev.ddthanh.jobsgobe.model.entity.UsedPackageEntity;
import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.packagee.PackageRepository;
import dev.ddthanh.jobsgobe.repository.usedPackage.UsedPackageRepository;
import dev.ddthanh.jobsgobe.repository.user.UserRepository;
import dev.ddthanh.jobsgobe.service.iservice.UsedPackageIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UsedPackageService implements UsedPackageIService {
    private final UsedPackageRepository usedPackageRepository;
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;

    public Date getExpiredDate(Date date, int duration) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, duration);
        return c.getTime();
    }
    @Override
    public Response<UsedPackageEntity> create(Long packageId, Long recruiterId, String vnpTxnRef) {
        PackageEntity packageEntity = packageRepository.findById(packageId).orElse(null);
        UserEntity recruiter = userRepository.findById(recruiterId).orElse(null);

        UsedPackageEntity usedPackageEntity = UsedPackageEntity.builder()
                .date_start(new Date())
                .date_end(getExpiredDate(new Date(), packageEntity.getDuration()))
                .status(false)
                .vnpTxnRef(vnpTxnRef)
                .recruiter(recruiter)
                .packageEntity(packageEntity)
                .build();
        usedPackageRepository.save(usedPackageEntity);
        return Response.<UsedPackageEntity>builder()
                .setData(usedPackageEntity)
                .setMessage("create used success")
                .build();
    }

    @Override
    public Response<UsedPackageEntity> updateStatus(String vnpTxnRef, boolean status) {
        UsedPackageEntity usedPackageEntity = usedPackageRepository.findByVnpTxnRef(vnpTxnRef);
        usedPackageEntity.setStatus(status);
        usedPackageRepository.save(usedPackageEntity);
        return Response.<UsedPackageEntity>builder()
                .setData(usedPackageEntity)
                .setMessage("update used success")
                .build();
    }

    @Override
    public Response<UsedPackageEntity> checkUsedPackage(Long id) {
        UserEntity recruiter = userRepository.findById(id).orElse(null);
        Date now = new Date();
        for (UsedPackageEntity usedPackageEntity: recruiter.getListUsedPackage()) {
            if(usedPackageEntity.isStatus() == true
                    && now.compareTo(usedPackageEntity.getDate_start()) >= 0
                    && now.compareTo(usedPackageEntity.getDate_end()) <= 0) {
                return Response.<UsedPackageEntity>builder()
                        .setData(usedPackageEntity)
                        .setMessage("Used")
                        .build();
            } else {
                usedPackageEntity.setStatus(false);
                usedPackageRepository.save(usedPackageEntity);
            }
        }
        return Response.<UsedPackageEntity>builder()
                .setSuccess(false)
                .setStatusCode(400)
                .setStatus(HttpStatus.BAD_REQUEST)
                .setMessage("No used")
                .build();
    }

    @Override
    public void cancelAllPackageByRecruiterId(Long id) {
        UserEntity recruiter = userRepository.findById(id).orElse(null);
        for (UsedPackageEntity usedPackageEntity: recruiter.getListUsedPackage()) {
                usedPackageEntity.setStatus(false);
                usedPackageRepository.save(usedPackageEntity);
        }
    }
}
