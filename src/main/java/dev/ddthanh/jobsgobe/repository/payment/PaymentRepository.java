package dev.ddthanh.jobsgobe.repository.payment;

import dev.ddthanh.jobsgobe.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    PaymentEntity findByVnpTxnRef(String vnpTxnRef);
}
