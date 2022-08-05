package com.service.gateway.controller;

import com.service.gateway.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.gateway.service.GatewayService;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class GatewayController {
    private final GatewayService gatewayService;

    @GetMapping
    public ApiResponse getSign() {
        return gatewayService.sendMessageAndGetResponse();
    }
}
