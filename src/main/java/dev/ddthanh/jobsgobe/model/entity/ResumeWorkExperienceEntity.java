package dev.ddthanh.jobsgobe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_resume_work_experience")
public class ResumeWorkExperienceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameCompany;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private Date startDay;
    private Date endDay;
    @Column(nullable = false)
    private boolean statusWork;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
}
