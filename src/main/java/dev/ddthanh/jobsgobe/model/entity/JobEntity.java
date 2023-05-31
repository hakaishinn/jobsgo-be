package dev.ddthanh.jobsgobe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String required;
    @Column(columnDefinition = "text")
    private String benefit;
    private String city;
    private String districts;
    private String wards;
    private String specificAddress;
    private String phone;
    private String certificate;
    private String position;
    private String positionWork;
    private Integer gender;
    private String ageStart;
    private String ageEnd;
    private Double numberYearExperienceStart;
    private Double numberYearExperienceEnd;
    private String salary;
}
