package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.model.entity.PaymentEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.payment.PaymentResponse;

import java.util.List;

public interface PaymentIService {
    public Response<PaymentEntity> create(Long packageId, Long recruiterId, Integer quantity, Long total, String vnpPayDate);
    public Response<PaymentEntity> updateStatus(String vnpPayDate, boolean status);
    public Response<List<PaymentResponse>>  showAllPayment();

    public Response<List<PaymentResponse>> showPaymentById(Long id);
    public void deleteByVnpTxnRef(String vnpTxnRef);
}
