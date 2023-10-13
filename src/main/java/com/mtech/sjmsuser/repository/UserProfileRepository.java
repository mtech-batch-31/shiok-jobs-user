package com.mtech.sjmsuser.repository;

import com.mtech.sjmsuser.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findById(Long id);

    Optional<UserProfile> findByAccountUuid(String accountUuid);
}