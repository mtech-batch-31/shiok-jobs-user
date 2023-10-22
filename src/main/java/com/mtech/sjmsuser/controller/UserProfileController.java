package com.mtech.sjmsuser.controller;

import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@CrossOrigin
public class UserProfileController {

    private final UserProfileService userProfileService; // Inject the service

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/v1/user/me")
    public ResponseEntity<UserProfileDto> getUserProfile(@RequestHeader("user-id") String userId) {
            log.info("getUserProfile called with userId " + userId);
            UserProfileDto userProfileDto = userProfileService.findByAccountUuid(userId);
        if (userProfileDto != null) {
            return ResponseEntity.ok(userProfileDto);
        } else {
            UserProfileDto errorResponse = new UserProfileDto();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/v1/user")
    public ResponseEntity<UserProfileDto> updateUser(@RequestHeader HttpHeaders headers, @Validated @RequestBody UpdateUserDto updateUserDto) {
        log.info("updateUser called with headers={},body={}",headers,updateUserDto);
        String userId = headers.getFirst("user-id");
        log.info("updateUser called with userId={}", userId);
        UserProfileDto userProfileDto = userProfileService.updateUserProfile(userId, updateUserDto);
        return ResponseEntity.ok(userProfileDto);
    }
}
