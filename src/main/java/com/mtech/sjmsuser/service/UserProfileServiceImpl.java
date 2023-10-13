package com.mtech.sjmsuser.service;

import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.mappers.UserProfileMapper;
import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.repository.UserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileDto retrieveUserProfile(long id) {
        Optional<UserProfile> userProfile = this.userProfileRepository.findById(id);
        if(userProfile.isEmpty()) {
            return null;
        }
        return UserProfileMapper.INSTANCE.toDto(userProfile.get());
    }

    @Override
    public UserProfileDto updateUserProfile(String accountUuid, UpdateUserDto updateUserDto) {
        // update userprofile data
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByAccountUuid(accountUuid);
        if (userProfileOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        UserProfile userProfile = userProfileOptional.get();
        userProfile.setSeeking(updateUserDto.isSeekingJob());
        userProfileRepository.saveAndFlush(userProfile);

        // TODO: integration with AWS SQS
        return UserProfileMapper.INSTANCE.toDto(userProfile);
    }
}
