package dev.ddthanh.jobsgobe.repository.softSkill;

import dev.ddthanh.jobsgobe.model.entity.ResumeHobbyEntity;
import dev.ddthanh.jobsgobe.model.entity.SoftSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkillEntity, Long> {
}
