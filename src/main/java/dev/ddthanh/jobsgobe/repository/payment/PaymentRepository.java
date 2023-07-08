package dev.ddthanh.jobsgobe.repository.payment;

import dev.ddthanh.jobsgobe.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    PaymentEntity findByVnpTxnRef(String vnpTxnRef);
    @Query(value = "select p from PaymentEntity p where p.recruiter.id = :id")
    List<PaymentEntity> findByIdUser(Long id);
}
