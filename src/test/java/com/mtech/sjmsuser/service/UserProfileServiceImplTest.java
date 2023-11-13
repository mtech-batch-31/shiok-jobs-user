package com.mtech.sjmsuser.service;

import com.mtech.sjmsuser.entity.EducationalExperience;
import com.mtech.sjmsuser.entity.UserProfile;
import com.mtech.sjmsuser.entity.WorkingExperience;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        MockitoAnnotations.openMocks(this);
    }

    // Write tests for findByAccountUuid method
    @Test
    public void testFindByAccountUuid() {
        // Create a UserProfile instance for testing
        var workingExperience = new WorkingExperience();
        workingExperience.setLogo("logo");
        workingExperience.setExperience("experience");
        workingExperience.setJobTitle("jobTitle");
        workingExperience.setYearStart("start");
        workingExperience.setYearEnd("end");
        workingExperience.setCompany("company");
        workingExperience.setId(2L);

        var workingExperiences = new ArrayList<WorkingExperience>();
        workingExperiences.add(workingExperience);

        var educationalExperience = new EducationalExperience();
        educationalExperience.setId(1L);
        educationalExperience.setLogo("logo");
        educationalExperience.setDescription("description");
        educationalExperience.setSchool("school");
        educationalExperience.setYearStart("start");
        educationalExperience.setYearEnd("end");


        var educationalExperiences = new ArrayList<EducationalExperience>();
        educationalExperiences.add(educationalExperience);

        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");
        userProfile.setSeeking(false);
        userProfile.setId(1L);
        userProfile.setAbout("about");
        userProfile.setImage("image");
        userProfile.setEducationalExperience(educationalExperiences);
        userProfile.setWorkingExperience(workingExperiences);
        userProfile.setJobTitle("jobTitle");
        userProfile.setName("name");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.of(userProfile));

        // Call the method to be tested
        UserProfileDto result = userProfileService.findByAccountUuid("testAccountUuid");

        // Assertions to verify the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals("testAccountUuid", result.getAccountUuid());
        Assertions.assertEquals(userProfile.getAccountUuid(), result.getAccountUuid());
        Assertions.assertEquals(userProfile.getId(), result.getId());
        Assertions.assertEquals(userProfile.getName(), result.getName());
        Assertions.assertEquals(userProfile.getImage(), result.getImage());
        Assertions.assertEquals(userProfile.getAbout(), result.getAbout());
        Assertions.assertEquals(userProfile.getJobTitle(), result.getJobTitle());

        Assertions.assertNotNull(result.getEducationalExperience());
        Assertions.assertEquals(userProfile.getEducationalExperience().size(), result.getEducationalExperience().size());
        Assertions.assertEquals(userProfile.getEducationalExperience().get(0).getId(), result.getEducationalExperience().get(0).getId());
        Assertions.assertEquals(userProfile.getEducationalExperience().get(0).getSchool(), result.getEducationalExperience().get(0).getSchool());
        Assertions.assertEquals(userProfile.getEducationalExperience().get(0).getLogo(), result.getEducationalExperience().get(0).getLogo());
        Assertions.assertEquals(userProfile.getEducationalExperience().get(0).getYearStart(), result.getEducationalExperience().get(0).getYearStart());
        Assertions.assertEquals(userProfile.getEducationalExperience().get(0).getYearEnd(), result.getEducationalExperience().get(0).getYearEnd());
        Assertions.assertEquals(userProfile.getEducationalExperience().get(0).getDescription(), result.getEducationalExperience().get(0).getDescription());

        Assertions.assertNotNull(result.getWorkingExperience());
        Assertions.assertEquals(userProfile.getWorkingExperience().size(), result.getWorkingExperience().size());
        Assertions.assertEquals(userProfile.getWorkingExperience().get(0).getId(), result.getWorkingExperience().get(0).getId());
        Assertions.assertEquals(userProfile.getWorkingExperience().get(0).getCompany(), result.getWorkingExperience().get(0).getCompany());
        Assertions.assertEquals(userProfile.getWorkingExperience().get(0).getLogo(), result.getWorkingExperience().get(0).getLogo());
        Assertions.assertEquals(userProfile.getWorkingExperience().get(0).getYearStart(), result.getWorkingExperience().get(0).getYearStart());
        Assertions.assertEquals(userProfile.getWorkingExperience().get(0).getYearEnd(), result.getWorkingExperience().get(0).getYearEnd());
        Assertions.assertEquals(userProfile.getWorkingExperience().get(0).getExperience(), result.getWorkingExperience().get(0).getExperience());
        Assertions.assertEquals(userProfile.getWorkingExperience().get(0).getJobTitle(), result.getWorkingExperience().get(0).getJobTitle());
    }

    @Test
    public void testFindByAccountUuid_UserProfileDoesNotExist() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> userProfileService.findByAccountUuid("testAccountUuid"));
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

    @Test
    public void testUpdateUserProfile_UserProfileDoesNotExist_ThrowException() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid(Mockito.anyString()))
                .thenReturn(Optional.empty());

        // Define the behavior of snsService.sendMessageToSnsTopic
        Mockito.doNothing().when(snsService).sendMessageToSnsTopic(Mockito.anyString());

        // Create an UpdateUserDto for testing
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setSeekingJob(true);

        Assertions.assertThrows(ResponseStatusException.class, () -> userProfileService.updateUserProfile("testAccountUuid", updateUserDto));

    }
}
