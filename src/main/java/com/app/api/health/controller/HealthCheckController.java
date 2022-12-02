package com.app.api.health.controller;

import com.app.api.health.dto.HealthCheckResponseDto;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    private final Environment environment;

    public HealthCheckController(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> healthCheck() {
        HealthCheckResponseDto healthCheckResponseDto = HealthCheckResponseDto.builder()
                .health("ok")
                .activeProfile(Arrays.asList(environment.getActiveProfiles()))
                .build();
        return ResponseEntity.ok(healthCheckResponseDto);
    }
}
