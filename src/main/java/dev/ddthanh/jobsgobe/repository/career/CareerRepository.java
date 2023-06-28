package dev.ddthanh.jobsgobe.repository.career;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<CareerEntity, Long> {
    @Query(value = "select c from CareerEntity c")
    List<CareerEntity> findTop(Pageable pageable);
}
