package dev.ddthanh.jobsgobe.repository.proSkill;

import dev.ddthanh.jobsgobe.model.entity.ProSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProSkillRepository extends JpaRepository<ProSkillEntity, Long> {
}
