package com.mtech.sjmsuser.mappers;

import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.model.UserProfileDto;
import org.mapstruct.Mapper;
@Mapper
public interface UserProfileMapper {

    static UserProfileDto toDto(UserProfile userProfile)
    {return UserProfileDto
            .builder()
            .userId(userProfile.getId())
            .email(userProfile.getEmail())
            .hashedPassword(userProfile.getHashedPassword())
            .role(userProfile.getRole())
            .build();
    }

//    default UserProfile toEntity(UserProfileDto userProfileDto)
//    {return UserProfile
//                .builder()
//                .userId(userProfileDto.getUserId())
//                .email(userProfileDto.getEmail())
//                .hashedPassword(userProfileDto.getHashedPassword())
//                .role(userProfileDto.getRole())
//                .build();
//    }
}