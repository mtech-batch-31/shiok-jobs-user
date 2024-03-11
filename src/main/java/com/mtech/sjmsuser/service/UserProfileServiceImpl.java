package com.mtech.sjmsuser.service;

import com.amazonaws.services.sns.model.AmazonSNSException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mtech.sjmsuser.entity.Education;
import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.entity.WorkExperience;
import com.mtech.sjmsuser.mappers.EducationMapper;
import com.mtech.sjmsuser.mappers.UserProfileMapper;
import com.mtech.sjmsuser.mappers.WorkExperienceMapper;
import com.mtech.sjmsuser.model.SnsUpdateUserDto;
import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.repository.EducationRepository;
import com.mtech.sjmsuser.repository.UserProfileRepository;
import com.mtech.sjmsuser.repository.WorkExperienceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private SnsService snsService;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

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
        userProfile.setSeeking(updateUserDto.getSeekingJob());
        userProfileRepository.saveAndFlush(userProfile);

        // integration with AWS SQS
        updateJobSeekingStatusSNS(accountUuid, updateUserDto.getSeekingJob());
        return UserProfileMapper.INSTANCE.toDto(userProfile);
    }

    @Transactional
    @Override
    public UserProfileDto saveUserProfile(UserProfileDto userProfileDto) {
        UserProfile newUserProfile = UserProfileMapper.INSTANCE.toEntity(userProfileDto);

        Optional<UserProfile> userProfileOptional = userProfileRepository.findByAccountUuid(userProfileDto.getAccountUuid());
        if (userProfileOptional.isPresent()){
            log.info("profile already exist, update existing profile");
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "profile already exist");
            UserProfile oldUserProfile = userProfileOptional.get();
            newUserProfile.setId(oldUserProfile.getId());
            educationRepository.deleteByUserProfile(oldUserProfile);
            workExperienceRepository.deleteByUserProfile(oldUserProfile);
        }

        //
        newUserProfile = userProfileRepository.saveAndFlush(newUserProfile);
        UserProfile finalNewUserProfile = newUserProfile;

        if (userProfileDto.getWorkExperience() != null) {
            // update working experience
            List<WorkExperience> workExperience = userProfileDto.getWorkExperience().stream()
                    .map(WorkExperienceMapper.INSTANCE::toEntity)
                    .collect(Collectors.toList());
            workExperience
                    .stream()
                    .forEach(w -> w.setUserProfile(finalNewUserProfile));
            newUserProfile.setWorkExperience(workExperience);
        }

        // update education
        if (userProfileDto.getEducation() != null){
            List<Education> education = userProfileDto.getEducation().stream()
                    .map(EducationMapper.INSTANCE::toEntity)
                    .collect(Collectors.toList());
            education.stream()
                    .forEach(e -> e.setUserProfile(finalNewUserProfile));
            newUserProfile.setEducation(education);
        }

        updateJobSeekingStatusSNS(newUserProfile.getAccountUuid(), newUserProfile.isSeeking());
        UserProfile updatedUserProfile = userProfileRepository.saveAndFlush(newUserProfile);
        log.info(String.valueOf(updatedUserProfile));
        return UserProfileMapper.INSTANCE.toDto(userProfileRepository.saveAndFlush(newUserProfile));
    }

    private void updateJobSeekingStatusSNS(String accountUuid, Boolean seekingJob){
        // integration with AWS SQS
        SnsUpdateUserDto snsUpdateUserDto = new SnsUpdateUserDto(accountUuid, seekingJob);
        String snsMessage;
        try {
            snsMessage = objectMapper.writeValueAsString(snsUpdateUserDto);
            snsService.sendMessageToSnsTopic(snsMessage);
        } catch (JsonProcessingException e) {
            log.error("error occurred while converting SnsUpdateUserDto to JSON string", e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred while updating job seeking status");
        } catch (AmazonSNSException e) {
            log.error("error occurred while sending message to aws sns", e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred while updating job seeking status");
        }
    }

}
