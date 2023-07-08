package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.model.entity.PackageEntity;
import dev.ddthanh.jobsgobe.model.entity.PaymentEntity;
import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.packagee.PackageRepository;
import dev.ddthanh.jobsgobe.repository.payment.PaymentRepository;
import dev.ddthanh.jobsgobe.repository.user.UserRepository;
import dev.ddthanh.jobsgobe.service.iservice.PaymentIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentIService {
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    @Override
    public Response<PaymentEntity> create(Long packageId, Long recruiterId,Long quantity, Long total,String vnpTxnRef) {
        PackageEntity packageEntity = packageRepository.findById(packageId).orElse(null);
        UserEntity recruiter = userRepository.findById(recruiterId).orElse(null);
        PaymentEntity payment = PaymentEntity.builder()
                .vnpTxnRef(vnpTxnRef)
                .dateCreate(new Date())
                .quantity(quantity)
                .total(total)
                .status(false)
                .recruiter(recruiter)
                .packageEntity(packageEntity)
                .build();
        paymentRepository.save(payment);
        return Response.<PaymentEntity>builder()
                .setMessage("Create payment success")
                .setData(payment)
                .build();
    }

    @Override
    public Response<PaymentEntity> updateStatus(String vnpTxnRef, boolean status) {
        PaymentEntity payment = paymentRepository.findByVnpTxnRef(vnpTxnRef);
        payment.setStatus(status);
        paymentRepository.save(payment);
        return Response.<PaymentEntity>builder()
                .setMessage("Update status payment success")
                .setData(payment)
                .build();
    }

    @Override
    public Response<List<PaymentEntity>> showAllPayment() {
        List<PaymentEntity> listPayment = paymentRepository.findAll();
        return Response.<List<PaymentEntity>>builder()
                .setMessage("Success")
                .setData(listPayment)
                .build();
    }

    @Override
    public Response<List<PaymentEntity>> showPaymentById(Long id) {
        List<PaymentEntity> listPaymentId = new ArrayList<>();
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity != null){
            listPaymentId = paymentRepository.findByIdUser(id);
            return Response.<List<PaymentEntity>>builder()
                    .setMessage("Success")
                    .setData(listPaymentId)
                    .build();
        }
        return Response.<List<PaymentEntity>>builder()
                .setMessage("Thất bại")
                .build();
    }

}
