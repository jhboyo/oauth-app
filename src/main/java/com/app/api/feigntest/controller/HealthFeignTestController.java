package com.app.api.feigntest.controller;


import com.app.api.feigntest.client.HelloClient;
import com.app.api.health.dto.HealthCheckResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HealthFeignTestController {

    private final HelloClient helloClient;

    public HealthFeignTestController(HelloClient helloClient) {
        this.helloClient = helloClient;
    }

    @GetMapping("/health/feign-test")
    public ResponseEntity<HealthCheckResponseDto> healthCheckTest() {
        HealthCheckResponseDto healthCheckResponseDto = helloClient.healthCheck();
        return ResponseEntity.ok(healthCheckResponseDto);
    }
}
