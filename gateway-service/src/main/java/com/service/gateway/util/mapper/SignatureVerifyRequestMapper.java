package com.service.gateway.util.mapper;

import com.service.gateway.util.dto.SignatureDto;
import com.service.gateway.util.dto.VerifyRequestDto;
import org.springframework.stereotype.Service;

@Service
public class SignatureVerifyRequestMapper {
    public VerifyRequestDto mapToVerifyRequestDto(SignatureDto signatureDto) {
        return VerifyRequestDto.builder()
                .algorithm(signatureDto.getAlgorithm())
                .publicKey(signatureDto.getPublicKey())
                .message(signatureDto.getMessage())
                .build();
    }

    public SignatureDto mapToSignatureDto(VerifyRequestDto verifyRequestDto) {
        return SignatureDto.builder()
                .algorithm(verifyRequestDto.getAlgorithm())
                .publicKey(verifyRequestDto.getPublicKey())
                .message(verifyRequestDto.getMessage())
                .build();
    }
}