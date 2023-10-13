package com.mtech.sjmsuser.mappers;

import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.model.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper {

    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    public UserProfileDto toDto(UserProfile userProfile);
}