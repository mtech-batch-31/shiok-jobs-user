package com.mtech.sjmsuser.service;

import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;

public interface UserProfileService {

    UserProfileDto findByAccountUuid(String accountUuid);

    UserProfileDto updateUserProfile(String accountUuid, UpdateUserDto updateUserDto);

    UserProfileDto saveUserProfile(UserProfileDto userProfileDto);
}
