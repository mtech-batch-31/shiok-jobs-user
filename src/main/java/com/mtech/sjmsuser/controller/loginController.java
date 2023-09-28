package com.mtech.sjmsuser.controller;

import com.mtech.sjmsuser.model.loginDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class loginController {
    @PostMapping("/login")
    public loginDto authenticate(@RequestBody loginDto request) {
        return loginDto.builder()
                .accessToken("1234")
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
