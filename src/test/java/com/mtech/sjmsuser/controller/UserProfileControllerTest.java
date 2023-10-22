package com.mtech.sjmsuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.service.UserProfileService;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileService userProfileService;

    @Test
    void giveSeekingJobStatus_return200Response() throws Exception{
        mockMvc.perform(put("/v1/user")
                .header("user-id", UUID.randomUUID())
                .contentType(ContentType.APPLICATION_JSON.toString())
                .content("{\"seekingJob\":false}"))
                .andExpect(status().isOk());
    }
}
