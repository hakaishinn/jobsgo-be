package dev.ddthanh.jobsgobe.repository.apply;

import dev.ddthanh.jobsgobe.model.entity.ApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {
    @Query(value = "select r from ApplyEntity r where r.status= :status")
    public List<ApplyEntity> showAllCandidateApprove(Integer status);
}
