package com.mtech.sjmsuser.repository;

import com.mtech.sjmsuser.entity.Education;
import com.mtech.sjmsuser.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {

    int deleteByUserProfile(UserProfile userProfile);

}