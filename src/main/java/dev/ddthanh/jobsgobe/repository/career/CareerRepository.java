package dev.ddthanh.jobsgobe.repository.career;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<CareerEntity, Long> {
}
