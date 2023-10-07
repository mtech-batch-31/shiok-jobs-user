package com.mtech.sjmsuser.service;

import com.mtech.sjmsuser.model.UserProfileDto;

public interface UserProfileService {

    UserProfileDto retrieveUserProfile(long id);
}
