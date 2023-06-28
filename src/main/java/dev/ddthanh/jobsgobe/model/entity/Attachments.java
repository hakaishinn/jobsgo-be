package dev.ddthanh.jobsgobe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_attachments")
public class Attachments {
    @Id
    private Long id;
    private String name;
    private String url;


    //Relationship
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resume;

}
