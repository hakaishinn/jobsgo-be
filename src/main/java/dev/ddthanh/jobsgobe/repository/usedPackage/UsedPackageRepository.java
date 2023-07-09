package dev.ddthanh.jobsgobe.repository.usedPackage;

import dev.ddthanh.jobsgobe.model.entity.UsedPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedPackageRepository extends JpaRepository<UsedPackageEntity, Long> {
    UsedPackageEntity findByVnpTxnRef(String vnpTxnRef);
}
