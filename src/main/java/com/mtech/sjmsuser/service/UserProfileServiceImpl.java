package com.mtech.sjmsuser.service;

import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.mappers.UserProfileMapper;
import com.mtech.sjmsuser.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    final private UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileDto retrieveUserProfile(long id) {
        Optional<UserProfile> UserProfile = this.userProfileRepository.findById(id);
        if(UserProfile.isEmpty()) {
            return null;
        }
        return UserProfileMapper.toDto(UserProfile.get());
    }
}
