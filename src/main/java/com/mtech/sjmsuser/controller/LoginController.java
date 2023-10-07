package com.mtech.sjmsuser.controller;

import com.mtech.sjmsuser.model.LoginDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class LoginController {
    @PostMapping("/login")
    public LoginDto authenticate(@RequestBody LoginDto request) {
        return LoginDto.builder()
                .accessToken("1234")
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
