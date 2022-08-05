package com.service.gateway.service;

import com.service.gateway.controller.ApiClient;
import com.service.gateway.model.ApiResponse;
import com.service.gateway.model.Topic;
import com.service.gateway.redis.repository.RedisRepository;
import com.service.gateway.util.DecodingService;
import com.service.gateway.util.MessageGenerator;
import com.service.gateway.util.dto.SignatureDto;
import com.service.gateway.util.dto.VerifyRequestDto;
import com.service.gateway.util.mapper.SignatureVerifyRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GatewayServiceImpl implements GatewayService {
    private final RedisRepository<String> redisRepository;
    private final MessageGenerator messageGenerator;
    private final DecodingService decodingService;
    private final SignatureVerifyRequestMapper signatureVerifyRequestMapper;
    private final ApiClient apiClient;

    private Topic createMessage() {
        return redisRepository.add(new String(messageGenerator.generateByteArray()));
    }

    private boolean verify(VerifyRequestDto verifyRequestDto) {
        String key = verifyRequestDto.getPublicKey();

        String s = redisRepository.get(key);

        SignatureDto signatureDto = signatureVerifyRequestMapper.mapToSignatureDto(verifyRequestDto);
        signatureDto.setSignature(s);

        try {
            return decodingService.isValidSignature(signatureDto);
        } catch (Exception e) {

        } finally {
            redisRepository.remove(key);
        }
        return false;
    }

    @Override
    public ApiResponse sendMessageAndGetResponse() {
        Topic message = createMessage();
        ApiResponse response;

        VerifyRequestDto verifyRequestDto = apiClient.signValue(message);
        boolean verified = verify(verifyRequestDto);
        if(verified) {
            response = ApiResponse.builder()
                    .message("Signature accepted")
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        else {
            response = ApiResponse.builder()
                    .message("Something went wrong")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        return response;
    }


}
