package com.service.gateway.util;

import com.service.gateway.util.dto.SignatureDto;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class DecodingService {

    public boolean isValidSignature(SignatureDto request) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(request.getAlgorithm());
        EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(request.getPublicKey()));
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        signature.initVerify(keyFactory.generatePublic(spec));
        signature.update(request.getMessage().getBytes());
        return signature.verify(Base64.getDecoder().decode(request.getSignature()));
    }
}
