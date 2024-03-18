package com.mtech.sjmsuser.controller;

import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserProfileController {

    private final UserProfileService userProfileService; // Inject the service

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/me")
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

    @PutMapping
    public ResponseEntity<UserProfileDto> updateUser(@RequestHeader HttpHeaders headers, @Validated @RequestBody UpdateUserDto updateUserDto) {
        log.info("updateUser called with headers={},body={}",headers,updateUserDto);
        String userId = headers.getFirst("user-id");
        log.info("updateUser called with userId={}", userId);
        UserProfileDto userProfileDto = userProfileService.updateUserProfile(userId, updateUserDto);
        return ResponseEntity.ok(userProfileDto);
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> saveUser(@RequestHeader HttpHeaders headers, @Validated @RequestBody UserProfileDto userProfileDto) {
        log.info("updateUser called with headers={},body={}",headers, userProfileDto);
        String userId = headers.getFirst("user-id");
        log.info("updateUser called with userId={}", userId);
        userProfileDto.setAccountUuid(userId);
        UserProfileDto createdUserProfileDto = userProfileService.saveUserProfile(userProfileDto);
        return ResponseEntity.ok(createdUserProfileDto);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok("OK");
    }
}
