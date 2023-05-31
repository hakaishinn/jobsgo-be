package dev.ddthanh.jobsgobe.model.entity;

import dev.ddthanh.jobsgobe.common.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String phone;
    private String city;
    private String districts;
    private String wards;
    private String specificAddress;
    private String emailCompany;
    private String shortName;
    private String website;
    private String facebook;
    private String twitter;
    private String linkedin;
    private String description;
    @Enumerated(EnumType.STRING)
    private Role role;

    //Relationship

    @OneToMany(targetEntity = ResumeEntity.class, mappedBy = "candidate")
    private Set<ResumeEntity> listResume;//for resume

    @OneToMany(targetEntity = JobEntity.class, mappedBy = "recruiter")
    private Set<JobEntity> listJob;//for job

    @OneToMany(targetEntity = PaymentEntity.class, mappedBy = "recruiter")
    private Set<PaymentEntity> listPayment;//for payment

    @OneToMany(targetEntity = UsedPackageEntity.class, mappedBy = "recruiter")
    private Set<UsedPackageEntity> listUsedPackage;//for usedPackage

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
