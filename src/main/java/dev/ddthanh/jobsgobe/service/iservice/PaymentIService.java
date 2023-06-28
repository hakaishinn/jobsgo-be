package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.PaymentEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;

public interface PaymentIService {
    public Response<PaymentEntity> create(Long packageId, Long recruiterId, Long quantity, Long total, String vnpPayDate);
    public Response<PaymentEntity> updateStatus(String vnpPayDate, boolean status);
}
