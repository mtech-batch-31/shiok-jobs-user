package com.mtech.sjmsuser.controller;

import com.mtech.sjmsuser.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sns")
public class SnsController {

    @Autowired
    private SnsService snsService;

    // for testing
    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        snsService.sendMessageToSnsTopic( message);
    }
}