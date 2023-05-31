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
@Table(name = "tbl_language")
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Relationship
    @OneToMany(targetEntity = ResumeLanguageEntity.class, mappedBy = "language")
    private Set<ResumeLanguageEntity> listResumeLanguage;//for resume language

    @ManyToMany(mappedBy = "listLanguage")
    private List<JobEntity> listJob;
}
