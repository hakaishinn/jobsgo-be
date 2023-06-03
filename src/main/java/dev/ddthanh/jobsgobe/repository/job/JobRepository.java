package dev.ddthanh.jobsgobe.repository.job;

import dev.ddthanh.jobsgobe.model.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
}