package dev.ddthanh.jobsgobe.repository.resume;

import dev.ddthanh.jobsgobe.model.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

}
