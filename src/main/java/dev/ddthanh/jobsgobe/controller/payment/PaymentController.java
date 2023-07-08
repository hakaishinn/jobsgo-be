package dev.ddthanh.jobsgobe.controller.payment;

import dev.ddthanh.jobsgobe.model.entity.PaymentEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping("/payments")
    public Response<List<PaymentEntity>> showAllPayment(){
        Response<List<PaymentEntity>> paymentResponse = paymentService.showAllPayment();
        return  paymentResponse;
    }
    @GetMapping("/payments/recruiter/{id}")
    public Response<List<PaymentEntity>> showPaymentById(@PathVariable Long id){
        Response<List<PaymentEntity>>paymentResponse = paymentService.showPaymentById(id);
        return  paymentResponse;

    }
}
