package com.mtech.sjmsuser.service;

import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;

public interface UserProfileService {

    UserProfileDto retrieveUserProfile(long id);

    UserProfileDto updateUserProfile(String accountUuid, UpdateUserDto updateUserDto);
}
