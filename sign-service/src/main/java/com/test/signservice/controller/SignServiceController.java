package com.test.signservice.controller;

import com.test.signservice.model.Topic;
import com.test.signservice.service.SignService;
import com.test.signservice.util.dto.VerifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class SignServiceController {
    private final SignService signService;

    @PostMapping
    public VerifyRequestDto getTopic(@RequestBody Topic topic) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return signService.getSign(topic);
    }
}
