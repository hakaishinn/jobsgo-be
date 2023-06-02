package dev.ddthanh.jobsgobe.repository.resume;

import dev.ddthanh.jobsgobe.model.entity.ResumeWorkExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeWorkExpRepository extends JpaRepository<ResumeWorkExperienceEntity, Long> {
}
