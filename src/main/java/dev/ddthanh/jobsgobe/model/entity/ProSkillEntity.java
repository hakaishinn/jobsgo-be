package dev.ddthanh.jobsgobe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_pro_skill")
public class ProSkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Relationship
    @OneToMany(targetEntity = ResumeProSkillEntity.class, mappedBy = "proSkill")
    private Set<ResumeProSkillEntity> listResumeProSkill;//for resume pro skill

    @ManyToMany(mappedBy = "listProSkill")
    private List<JobEntity> listJob; // for job

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id")
    private CareerEntity career;
}
