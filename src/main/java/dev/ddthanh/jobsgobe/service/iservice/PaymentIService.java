package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.PaymentEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;

import java.util.List;

public interface PaymentIService {
    public Response<PaymentEntity> create(Long packageId, Long recruiterId, Long quantity, Long total, String vnpPayDate);
    public Response<PaymentEntity> updateStatus(String vnpPayDate, boolean status);
    public Response<List<PaymentEntity>>  showAllPayment();

    public Response<List<PaymentEntity>> showPaymentById(Long id);
}
