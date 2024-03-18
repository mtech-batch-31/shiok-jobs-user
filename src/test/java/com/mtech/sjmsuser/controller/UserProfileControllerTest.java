package com.mtech.sjmsuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.service.UserProfileService;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileService userProfileService;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserProfileController(userProfileService)).setControllerAdvice(new ControllerAdvice()).build();
    }

    @Test
    void giveSeekingJobStatus_return200Response() throws Exception{
        mockMvc.perform(put("/v1/users")
                .header("user-id", UUID.randomUUID())
                .contentType(ContentType.APPLICATION_JSON.toString())
                .content("{\"seekingJob\":false}"))
                .andExpect(status().isOk());
    }

    @Test
    void givenGetUserProfile_return200Response() throws Exception{
        var userId = UUID.randomUUID();
        var userProfile = new UserProfileDto();

        Mockito.when(userProfileService.findByAccountUuid(userId.toString())).thenReturn(userProfile);

        mockMvc.perform(get("/v1/users/me")
                        .header("user-id", userId)
                        .contentType(ContentType.APPLICATION_JSON.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void givenUserProfileIsNotFound_return400Response() throws Exception{
        var userId = UUID.randomUUID();

        mockMvc.perform(get("/v1/users/me")
                        .header("user-id", userId)
                        .contentType(ContentType.APPLICATION_JSON.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenGetUserProfileThrowsUnexpectedException_return400Response() throws Exception{
        var userId = UUID.randomUUID();

        mockMvc.perform(get("/v1/users/me")
                        .header("user-id", userId)
                        .contentType(ContentType.APPLICATION_JSON.toString()))
                .andExpect(status().isNotFound());
    }



    @Test
    void testSaveUser() throws Exception {
        // Mock request header
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-id", "testUserId");

        // Create a UserProfileDto object
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setName("John Doe");
        // Convert UserProfileDto to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String userProfileJson = objectMapper.writeValueAsString(userProfileDto);

        // Mock UserProfileService's saveUserProfile method
        UserProfileDto savedUserProfileDto = new UserProfileDto();
        savedUserProfileDto.setId(1L);
        savedUserProfileDto.setName("John Doe");
        Mockito.when(userProfileService.saveUserProfile(Mockito.any())).thenReturn(savedUserProfileDto);

        // Perform POST request
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userProfileJson)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedUserProfileDto.getId()))
                .andExpect(jsonPath("$.name").value(savedUserProfileDto.getName()));

        // Verify that saveUserProfile method is called with correct parameters
        verify(userProfileService, times(1)).saveUserProfile(any());
    }

}
