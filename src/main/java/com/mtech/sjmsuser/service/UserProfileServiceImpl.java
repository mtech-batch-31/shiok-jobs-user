package com.mtech.sjmsuser.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.mappers.UserProfileMapper;
import com.mtech.sjmsuser.model.SnsUpdateUserDto;
import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SnsService snsService;

    private ObjectMapper objectMapper = new ObjectMapper();

//    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
//        this.userProfileRepository = userProfileRepository;
//    }

//    public UserProfileDto retrieveUserProfile(long id) {
//        Optional<UserProfile> userProfile = this.userProfileRepository.findById(id);
//        if(userProfile.isEmpty()) {
//            return null;
//        }
//        return UserProfileMapper.INSTANCE.toDto(userProfile.get());
//    }



    @Override
    public UserProfileDto findByAccountUuid(String accountUuid) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByAccountUuid(accountUuid);
        if (userProfileOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        UserProfile userProfile = userProfileOptional.get();
        return UserProfileMapper.INSTANCE.toDto(userProfile);
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

        SnsUpdateUserDto snsUpdateUserDto = new SnsUpdateUserDto(accountUuid, updateUserDto.isSeekingJob());
        String snsMessage;
        try {
            snsMessage = objectMapper.writeValueAsString(snsUpdateUserDto);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred while sending message to aws sns");
        }

        // integration with AWS SQS
        snsService.sendMessageToSnsTopic(snsMessage);
        return UserProfileMapper.INSTANCE.toDto(userProfile);
    }
}
