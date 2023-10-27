package com.mtech.sjmsuser.service;

import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.repository.UserProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserProfileServiceImplTest {

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private SnsService snsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Write tests for findByAccountUuid method
    @Test
    public void testFindByAccountUuid() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.of(userProfile));

        // Call the method to be tested
        UserProfileDto result = userProfileService.findByAccountUuid("testAccountUuid");

        // Assertions to verify the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals("testAccountUuid", result.getAccountUuid());
    }

    // Write tests for updateUserProfile method
    @Test
    public void testUpdateUserProfile() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.of(userProfile));

        // Define the behavior of snsService.sendMessageToSnsTopic
        Mockito.doNothing().when(snsService).sendMessageToSnsTopic(Mockito.anyString());

        // Create an UpdateUserDto for testing
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setSeekingJob(true);

        // Call the method to be tested
        UserProfileDto result = userProfileService.updateUserProfile("testAccountUuid", updateUserDto);

        // Assertions to verify the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals("testAccountUuid", result.getAccountUuid());
        Assertions.assertTrue(result.isSeeking());
    }
}
