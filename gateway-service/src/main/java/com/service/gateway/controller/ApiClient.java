package com.service.gateway.controller;

import com.service.gateway.model.Topic;
import com.service.gateway.util.dto.VerifyRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "key-service", url = "http://localhost:8081")
public interface ApiClient {
    @PostMapping("/internal")
    VerifyRequestDto signValue(@RequestBody Topic topic);
}
