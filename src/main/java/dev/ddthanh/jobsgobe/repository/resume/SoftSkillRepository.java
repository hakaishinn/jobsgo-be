package dev.ddthanh.jobsgobe.repository.resume;

import dev.ddthanh.jobsgobe.model.entity.ResumeHobbyEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftSkillRepository extends JpaRepository<SoftSkillEntity, Long> {
}
