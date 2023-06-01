package dev.ddthanh.jobsgobe.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_resume_hobby")
public class ResumeHobbyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resume;

    public ResumeHobbyEntity(String name, ResumeEntity resume){
        super();
        this.name = name;
        this.resume = resume;
    }
}
