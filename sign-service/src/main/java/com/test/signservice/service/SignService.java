package com.test.signservice.service;

import com.test.signservice.model.Topic;
import com.test.signservice.util.dto.VerifyRequestDto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface SignService {

    VerifyRequestDto getSign(Topic topic) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, SignatureException, InvalidKeyException;
}
