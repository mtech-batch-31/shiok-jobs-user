package com.mtech.sjmsuser.repository;

import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    int deleteByUserProfile(UserProfile userProfile);

}