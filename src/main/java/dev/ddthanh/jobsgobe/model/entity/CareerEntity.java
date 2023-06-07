package dev.ddthanh.jobsgobe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tbl_career")
public class CareerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Relationship
    @JsonIgnore
    @ManyToMany(mappedBy = "listCareer")
    private List<JobEntity> listJob;

    @JsonIgnore
    @OneToMany(targetEntity = ProSkillEntity.class, mappedBy = "career")
    private Set<ProSkillEntity> listProSkill;//for pro skill
}
