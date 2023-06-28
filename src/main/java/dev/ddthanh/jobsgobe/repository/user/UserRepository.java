package dev.ddthanh.jobsgobe.repository.user;

import dev.ddthanh.jobsgobe.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    @Query(value = "select u from UserEntity u where u.role = 'ADMIN'")
    Optional<UserEntity> findAdmin();
}
