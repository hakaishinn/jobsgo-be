package dev.ddthanh.jobsgobe.repository.packagee;

import dev.ddthanh.jobsgobe.model.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity,Long> {
}
