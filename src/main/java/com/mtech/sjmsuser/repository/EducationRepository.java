package com.mtech.sjmsuser.repository;

import com.mtech.sjmsuser.entity.Education;
import com.mtech.sjmsuser.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EducationRepository extends JpaRepository<Education, UUID> {
//    Optional<UserProfile> findById(long id);

//    @Modifying
    int deleteByUserProfile(UserProfile userProfile);

}