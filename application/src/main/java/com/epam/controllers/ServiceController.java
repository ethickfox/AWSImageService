package com.epam.controllers;

import com.epam.service.cloud.CloudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/service")
public class ServiceController {
    private CloudService cloudService;

    @GetMapping("/")
    public Map<String, Object> home() {
        return cloudService.message();
    }
}
