package com.test.signservice.service;

import com.test.signservice.model.Topic;
import com.test.signservice.redis.repository.RedisRepository;
import com.test.signservice.util.EncodingService;
import com.test.signservice.util.dto.SignatureDto;
import com.test.signservice.util.dto.VerifyRequestDto;
import com.test.signservice.util.mapper.SignatureVerifyRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {
    private final RedisRepository<String> redisRepository;
    private final EncodingService encodingService;
    private final SignatureVerifyRequestMapper signatureVerifyRequestMapper;



    @Override
    public VerifyRequestDto getSign(Topic topic) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] array = new ByteArrayInputStream(redisRepository.getAndDelete(topic.getKey()).getBytes()).readAllBytes();

        SignatureDto signatureDto = encodingService.signSignature(array);
        redisRepository.add(signatureDto.getPublicKey(), signatureDto.getSignature());

        return signatureVerifyRequestMapper.mapToVerifyRequestDto(signatureDto);
    }
}
