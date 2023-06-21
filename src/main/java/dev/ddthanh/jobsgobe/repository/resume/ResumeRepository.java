package dev.ddthanh.jobsgobe.repository.resume;

import dev.ddthanh.jobsgobe.model.entity.ResumeEntity;
import dev.ddthanh.jobsgobe.payload.response.resume.ResumeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
    @Query(value = "select r from ResumeEntity r where r.candidate.id = :id")
    public List<ResumeEntity> showAllByCandidateId(Long id);
}
